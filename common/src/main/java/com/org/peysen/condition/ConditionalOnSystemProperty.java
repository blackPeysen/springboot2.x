package com.org.peysen.condition;


import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @Description: java类作用描述
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
