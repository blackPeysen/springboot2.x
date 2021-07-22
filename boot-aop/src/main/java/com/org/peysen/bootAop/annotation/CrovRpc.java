package com.org.peysen.bootAop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: peimengmeng
 * @Date: 2021/7/17_08:25
 * @Desc:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CrovRpc {
    /**
     * @return the per clause expression, defaults to singleton aspect.
     * Valid values are "" (singleton), "perthis(...)", etc
     */
    public String value() default "";
}