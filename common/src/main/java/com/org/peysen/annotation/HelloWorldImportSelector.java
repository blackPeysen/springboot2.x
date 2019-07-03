package com.org.peysen.annotation;

import com.org.peysen.configuration.HelloWorldConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Description: helloworld ImportSelector
 * @Author: peimm
 * @CreateDate: 2019/7/3 07:12
 * @UpdateRemark: The modified content
 */

public class HelloWorldImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {

        return new String[]{HelloWorldConfiguration.class.getName()};
    }
}
