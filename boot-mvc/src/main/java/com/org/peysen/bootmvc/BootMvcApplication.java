package com.org.peysen.bootmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = "com.org.peysen.bootmvc.config")
public class BootMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootMvcApplication.class, args);
    }

}
