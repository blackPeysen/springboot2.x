package com.org.peysen.bootmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableWebMvc
//@ServletComponentScan(basePackages = "com.org.peysen.bootmvc.web.servlet")
@SpringBootApplication
public class BootMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootMvcApplication.class, args);
    }

}
