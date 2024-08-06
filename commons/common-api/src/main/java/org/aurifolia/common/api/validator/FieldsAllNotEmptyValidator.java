package org.aurifolia.common.api.validator;

import org.aurifolia.common.api.annotation.FieldsAllNotEmpty;
import org.aurifolia.common.api.util.ValidationUtil;

import java.lang.invoke.MethodHandle;
import java.util.List;
import java.util.Set;

/**
 * @author danpeng
 * @since 1.0
 */
public class FieldsAllNotEmptyValidator extends FieldsUnionValidator<FieldsAllNotEmpty, Object> {
    @Override
    public Set<String> getFieldNames(Class<?> aClass) {
        return Set.of(aClass.getDeclaredAnnotation(FieldsAllNotEmpty.class).fieldNames());
    }

    @Override
    public boolean isValid(Object value, List<MethodHandle> methodHandles) {
        for (MethodHandle methodHandle : methodHandles) {
            try {
                if (ValidationUtil.isEmpty(methodHandle.invoke(value))) {
                    return false;
                }
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
}
