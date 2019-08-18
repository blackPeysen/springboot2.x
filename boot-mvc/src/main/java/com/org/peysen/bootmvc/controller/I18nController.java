package com.org.peysen.bootmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RequestMapping("/i18n")
@RestController
public class I18nController {
    @Autowired
    private ResourceBundleMessageSource messageSource;

    @RequestMapping(value = "/helloI18n",method = RequestMethod.GET)
    public String helloI18n(Locale locale){
        String message = messageSource.getMessage("login.username", null, locale);
        System.out.println("message:" + message);
        return "hello i18n:" + locale.toString();
    }
}
