package org.aurifolia.common.api.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.aurifolia.common.api.annotation.FieldsAllNotEmptyThenAllNotEmpty;
import org.aurifolia.common.api.annotation.FieldsNotAllEmpty;
import org.aurifolia.common.api.annotation.FieldsNotAllEmptyThenAllNotEmpty;

import java.util.Set;

/**
 * 条件校验，如果condition成立，就校验rela
 *
 * @author danpeng
 * @since 1.0
 */
public class FieldsNotAllEmptyThenAllNotEmptyValidator implements ConstraintValidator<FieldsNotAllEmptyThenAllNotEmpty, Object> {
    private FieldsNotAllEmptyValidator conditionValidator;
    private FieldsAllNotEmptyValidator relaValidator;

    @Override
    public void initialize(FieldsNotAllEmptyThenAllNotEmpty constraintAnnotation) {
        conditionValidator = new FieldsNotAllEmptyValidator() {
            @Override
            public Set<String> getFieldNames(Class<?> aClass) {
                return Set.of(constraintAnnotation.condition().fieldNames());
            }
        };
        relaValidator = new FieldsAllNotEmptyValidator() {
            @Override
            public Set<String> getFieldNames(Class<?> aClass) {
                return Set.of(constraintAnnotation.rela().fieldNames());
            }
        };
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return !conditionValidator.isValid(value, context) || relaValidator.isValid(value, context);
    }
}
