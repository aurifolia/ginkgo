package org.aurifolia.common.api.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.aurifolia.common.api.validator.FieldsAllNotEmptyThenAllNotEmptyValidator;
import org.aurifolia.common.api.validator.FieldsNotAllEmptyThenAllNotEmptyValidator;

import java.lang.annotation.*;

/**
* 
* 当指定字段列表的值不全为空时, 校验与之关联的字段列表的值都不为空
 *
* @author danpeng
* @since 1.0
*/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldsNotAllEmptyThenAllNotEmptyValidator.class)
@Repeatable(FieldsNotAllEmptyThenAllNotEmpty.List.class)
public @interface FieldsNotAllEmptyThenAllNotEmpty {
    /**
     * 提示信息
     *
     * @return 提示信息
     */
    String message() default "Fields value can not be empty";

    /**
     * 分组
     *
     * @return 分组集合
     */
    Class<?>[] groups() default {};

    /**
     * 负载
     *
     * @return 负载集合
     */
    Class<? extends Payload>[] payload() default {};
    /**
     * 条件FieldsNotAllEmpty, 如果fieldNames的值不全为空, 就会校验rela里fieldNames的值全不为空
     *
     * @return 条件FieldsNotAllEmpty
     */
    FieldsNotAllEmpty condition();

    /**
     * 关联的FieldsAllNotEmpty
     *
     * @return 关联FieldsAllNotEmpty
     */
    FieldsAllNotEmpty rela();

    /**
     * FieldsAllNotEmptyConditional列表
     */
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface List {
        FieldsNotAllEmptyThenAllNotEmpty[] value();
    }
}
