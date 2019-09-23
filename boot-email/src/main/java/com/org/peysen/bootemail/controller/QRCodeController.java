package com.org.peysen.bootemail.controller;

import com.org.peysen.bootemail.service.QRCodeService;
import com.org.peysen.bootemail.utils.QRCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Description: java类作用描述
 * Created by mengmeng.Pei
 * 2019/9/23 10:59
 */
@RestController
@RequestMapping("/qrcode")
public class QRCodeController {

    @Autowired
    private QRCodeService qrCodeService;

    @RequestMapping(value="/getQRCode")
    public String getQRCode() throws IOException {
        return qrCodeService.crateQRCode("this is qrcode",200,200);
    }

    @RequestMapping("/createQrCode")
    public void createQrCode(String logoPath, HttpServletRequest request, HttpServletResponse response) {
        StringBuffer url = request.getRequestURL();
        try {
            OutputStream os = response.getOutputStream();
            //从配置文件读取需要生成二维码的连接
//            String requestUrl = GraphUtils.getProperties("requestUrl");

            /**
             * requestUrl:需要生成二维码的连接，
             * logoPath：内嵌图片的路径，
             * os：响应输出流，
             * needCompress:是否压缩内嵌的图片
             */
            QRCodeUtil.encode("You're an idiot", logoPath, os,true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
