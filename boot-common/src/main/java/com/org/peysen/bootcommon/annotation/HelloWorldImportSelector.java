package com.org.peysen.bootcommon.annotation;

import com.org.peysen.bootcommon.configuration.HelloWorldConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @Description: helloworld {@link ImportSelector} 实现
 * @Author: peimm
 * @CreateDate: 2019/7/3 07:12
 * @UpdateRemark: 可以更具有弹性的选择返回想要的类型
 */

public class HelloWorldImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {

        return new String[]{HelloWorldConfiguration.class.getName()};
    }
}
