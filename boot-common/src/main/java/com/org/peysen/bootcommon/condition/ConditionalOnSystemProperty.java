package com.org.peysen.bootcommon.condition;


import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @Description: java系统属性 条件判断
 * @Author: peimm
 * @CreateDate: 2019/7/3 07:46
 * @UpdateRemark: The modified content
 */

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnSystemPropertyCondition.class)
public @interface ConditionalOnSystemProperty {

    String name() default "";

    //java系统属性值
    String value() default "";
}
