package org.aurifolia.common.api.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.aurifolia.common.api.validator.VerifiedByStaticMethodValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 通过静态方法校验字段
 *
 * @author danpeng
 * @since 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VerifiedByStaticMethodValidator.class)
public @interface VerifiedBy {
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
     * 静态方法全路径, 静态方法只有1个入参, 返参为boolean或Boolean
     *
     * @return 静态方法全路径
     */
    String staticMethodFullName();

    /**
     * VerifiedBy列表
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface List {
        VerifiedBy[] value();
    }
}
