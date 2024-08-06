package org.aurifolia.common.api.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.aurifolia.common.api.validator.FieldsAllNotEmptyValidator;
import org.aurifolia.common.api.validator.FieldsNotAllEmptyValidator;

import java.lang.annotation.*;

/**
 * 校验字段全不为空
 *
 * @author danpeng
 * @since 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldsAllNotEmptyValidator.class)
@Repeatable(FieldsAllNotEmpty.List.class)
public @interface FieldsAllNotEmpty {
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
     * 字段名列表, 在列表里的字段会被校验同时不为空, 校验规则如下:
     * 1. String: 不为null并且长度不为0
     * 2. 数组: 不为null并且长度不为0
     * 3. 集合: 不为null并且长度不为0
     * 4. 单个对象: 不为null
     *
     * @return 字段名列表
     */
    String[] fieldNames();

    /**
     * 列表
     */
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface List {
        FieldsAllNotEmpty[] value();
    }
}