package org.aurifolia.ginkgo.commons.security;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 开启ginkgo的统一权限管理
 *
 * @author VNElinpe
 * @since 1.0
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//@Import()
public @interface EnableGinkgoSecurity {
}
