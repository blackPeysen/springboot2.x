package com.org.peysen.bootmvc.annotation;

@java.lang.annotation.Target(java.lang.annotation.ElementType.TYPE)
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Documented
@org.springframework.stereotype.Service
public @interface OrderHandlerType {
    String source();
    String payMethod();
}
