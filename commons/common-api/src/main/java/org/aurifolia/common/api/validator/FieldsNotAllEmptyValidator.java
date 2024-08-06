package org.aurifolia.common.api.validator;

import org.aurifolia.common.api.annotation.FieldsNotAllEmpty;
import org.aurifolia.common.api.util.ValidationUtil;

import java.lang.invoke.MethodHandle;
import java.util.*;

/**
 * 多个字段不同时为空的校验器
 *
 * @author danpeng
 * @since 1.0
 */
public class FieldsNotAllEmptyValidator extends FieldsUnionValidator<FieldsNotAllEmpty, Object> {
    @Override
    public Set<String> getFieldNames(Class<?> aClass) {
        return Set.of(aClass.getDeclaredAnnotation(FieldsNotAllEmpty.class).fieldNames());
    }

    @Override
    public boolean isValid(Object value, List<MethodHandle> methodHandles) {
        for (MethodHandle methodHandle : methodHandles) {
            try {
                if (!ValidationUtil.isEmpty(methodHandle.invoke(value))) {
                    return true;
                }
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
