package org.aurifolia.common.api.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.aurifolia.common.api.annotation.VerifiedBy;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用静态方法校验参数
 *
 * @author danpeng
 * @since 1.0
 */
public class VerifiedByStaticMethodValidator implements ConstraintValidator<VerifiedBy, Object> {
    private Map<Class<?>, MethodHandle> methodHandleMap;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        MethodHandle methodHandle = methodHandleMap.get(value.getClass());
        if (methodHandle == null) {
            throw new IllegalArgumentException("cannot validate value");
        }
        try {
            return Boolean.TRUE.equals(methodHandle.invoke(value));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(VerifiedBy constraintAnnotation) {
        methodHandleMap = new HashMap<>();
        String staticMethodFullName = constraintAnnotation.staticMethodFullName();
        int separator = staticMethodFullName.lastIndexOf(".");
        String className = staticMethodFullName.substring(0, separator);
        String methodName = staticMethodFullName.substring(separator + 1);
        try {
            Class<?> aClass = Class.forName(className);
            MethodHandles.Lookup lookup = MethodHandles.privateLookupIn(aClass, MethodHandles.lookup());
            for (Method declaredMethod : aClass.getDeclaredMethods()) {
                if (!declaredMethod.getName().equals(methodName) || !Modifier.isStatic(declaredMethod.getModifiers())) {
                    continue;
                }
                Parameter[] parameters = declaredMethod.getParameters();
                if (parameters.length != 1) {
                    continue;
                }
                methodHandleMap.put(parameters[0].getType(), lookup.findStatic(aClass, methodName,
                        MethodType.methodType(declaredMethod.getReturnType(), parameters[0].getType())));
            }
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        if (methodHandleMap.isEmpty()) {
            throw new IllegalArgumentException("cannot use static method");
        }
    }
}
