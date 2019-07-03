package com.org.peysen.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: peimm
 * @CreateDate: 2019/7/3 07:47
 * @UpdateRemark: The modified content
 */
public class OnSystemPropertyCondition  implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(ConditionalOnSystemProperty.class.getName());

        String name = String.valueOf(annotationAttributes.get("name"));
        String value = String.valueOf(annotationAttributes.get("value"));

        String property = System.getProperty(name);


        return !value.equals(property);
    }

}
