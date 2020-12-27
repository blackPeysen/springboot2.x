package com.org.peysen.bootmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Description: java类作用描述
 * Created by mengmeng.Pei
 * 2019/8/6 15:14
 */

@Controller
public class HelloController {

    @ModelAttribute
    private void test(@PathVariable("id") Integer id, Map<String,Object> map){
        if(id != null){
            map.put("id",2);
        }else{
            map.put("id",1);
        }
    }

    @RequestMapping("/helloWorld/{id}")
    public String helloWorld(@ModelAttribute("id") Integer id){
        System.out.println("helloWorld:" + id);
        return "index";
    }

    @RequestMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        response.getOutputStream().write("index".getBytes());
    }

}
