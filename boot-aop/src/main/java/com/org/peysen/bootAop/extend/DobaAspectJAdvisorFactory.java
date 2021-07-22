package com.org.peysen.bootAop.extend;

import com.org.peysen.bootAop.annotation.CrovRpc;
import org.springframework.aop.aspectj.annotation.ReflectiveAspectJAdvisorFactory;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.Objects;

/**
 * @Auther: peimengmeng
 * @Date: 2021/7/17_08:56
 * @Desc:
 */
public class DobaAspectJAdvisorFactory extends ReflectiveAspectJAdvisorFactory {

    @Override
    public boolean isAspect(Class<?> clazz){
        CrovRpc annotation = AnnotationUtils.findAnnotation(clazz, CrovRpc.class);
        return Objects.nonNull(annotation) || super.isAspect(clazz);
    }

}
