package com.org.peysen.bootAop;

import com.org.peysen.bootAop.annotation.DobaEnableAspectJAutoProxy;
import com.org.peysen.bootAop.service.AspectJServiceImpl;
import com.org.peysen.bootAop.service.CrovRpcServiceImpl;
import com.org.peysen.bootAop.service.CrovServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class BootAopApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(BootAopApplication.class, args);

//        AspectJServiceImpl aspectJServiceImpl = applicationContext.getBean("aspectJServiceImpl", AspectJServiceImpl.class);
//        aspectJServiceImpl.test1();

        CrovRpcServiceImpl crovService = applicationContext.getBean(CrovRpcServiceImpl.class);
        crovService.remoteRpcInvoke();

        applicationContext.close();
    }

}
