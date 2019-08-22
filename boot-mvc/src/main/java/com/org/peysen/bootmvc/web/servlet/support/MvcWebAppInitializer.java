package com.org.peysen.bootmvc.web.servlet.support;

import com.org.peysen.bootmvc.config.DispatcherServletConfig;
import com.org.peysen.bootmvc.config.RootConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @Description: framework中使用注解驱动配置Servlet，用于替换web.xml
 * @Author: peysen
 * @CreateDate: 2019/7/23 20:59
 * @UpdateRemark: The modified content
 */
public class MvcWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { RootConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { DispatcherServletConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
