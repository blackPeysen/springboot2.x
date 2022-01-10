package com.org.peysen.bootemail.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 验证邮件激活链接
 * Created by mengmeng.Pei
 * 2019/9/23 9:57
 */

@RestController
@RequestMapping("/email")
public class EmailController {

    @RequestMapping(value = "/validation",method = RequestMethod.GET)
    public String validation(String token){

        return token;
    }


}
