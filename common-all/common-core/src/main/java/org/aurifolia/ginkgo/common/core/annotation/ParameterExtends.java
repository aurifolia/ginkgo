package org.aurifolia.ginkgo.common.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ；利用MessageSource填充返回数据
 *
 * @author danpeng
 * @since 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ParameterExtends {
    /**
     * 是否填充resultMessage, 返参是ResultDTO时才生效
     *
     * @return true/false
     */
    boolean fillResultMessage() default true;

    /**
     * 是否转换时间的时区，只有在http上下文并且请求头带有时区标识的情况下才生效
     *
     * @return true/false
     */
    boolean transformTimeZone() default false;
}
