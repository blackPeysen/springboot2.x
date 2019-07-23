package com.org.peysen.bootcommon.bootstrap.aspect;

import com.org.peysen.bootcommon.aspect.BlankDisc;
import com.org.peysen.bootcommon.aspect.config.AspectConfig;
import com.org.peysen.bootcommon.aspect.Performance;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Description: java类作用描述
 * @Author: peysen
 * @CreateDate: 2019/7/23 07:20
 * @UpdateRemark: The modified content
 */

@SpringBootApplication(scanBasePackageClasses = {AspectConfig.class})
public class aspectConfigurationBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(aspectConfigurationBootstrap.class).run(args);

//        Performance performance = context.getBean(Performance.class);
//        performance.perform();


        BlankDisc blankDisc = context.getBean(BlankDisc.class);
        blankDisc.playTrack(1);
        blankDisc.playTrack(1);
        blankDisc.playTrack(2);

        context.close();
    }

}
