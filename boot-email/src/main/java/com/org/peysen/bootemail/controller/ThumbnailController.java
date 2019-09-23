package com.org.peysen.bootemail.controller;

import com.org.peysen.bootemail.service.ThumbnailService;
import com.org.peysen.bootemail.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Description: java类作用描述
 * Created by mengmeng.Pei
 * 2019/9/23 14:08
 */
@Controller
@RequestMapping("/")
public class ThumbnailController {

    @Autowired
    private UploadService uploadService;

    @Resource(name = "thumbnailServiceImpl")
    private ThumbnailService thumbnailService;

    @RequestMapping(value="/thumbnail",method= RequestMethod.POST)
    public ModelAndView thumbnail(@RequestParam("image") CommonsMultipartFile file, HttpSession session) throws Exception{
        System.out.println("=========================");
        //主要针对于图片上传
        //相对路径
        String uploadPath="/images";
        //图片在服务器上的绝对路径信息
        String realUploadPath=session.getServletContext().getRealPath(uploadPath);
        //返回的结果
        //图片原图在服务器上访问的相对路径信息
        String imageUrl=uploadService.uploadImage(file, uploadPath, realUploadPath);

        //缩略图访问路径
        String thumImageUrl=thumbnailService.thumbnail(file, uploadPath, realUploadPath);

        //设置返回前端显示（渲染）
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("imageURL", imageUrl);
        modelAndView.addObject("thumImageURL", thumImageUrl);
        modelAndView.setViewName("thumbnail");

        return modelAndView;
    }
}
