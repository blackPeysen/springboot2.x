package com.org.peysen.bootemail.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Description: 文件上传文件的处理
 * Created by mengmeng.Pei
 * 2019/9/23 14:09
 */

@Service
public class UploadService {
    public String uploadImage(CommonsMultipartFile file, String uploadPath, String realUploadPath) {
        InputStream iStream = null;
        OutputStream oStream = null;

        try {
            //获取上传文件的流文件
            iStream = file.getInputStream();
            //创建文件输出流与位置
            String des = realUploadPath + "/" + file.getOriginalFilename();
            oStream = new FileOutputStream(des);
            byte[] buffer = new byte[1024];
            while (iStream.read(buffer) > 0) {
                oStream.write(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (iStream != null) {
                try {
                    iStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oStream != null) {
                try {
                    oStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //返回文件上传的相对路径
        return uploadPath + "/" + file.getOriginalFilename();
    }
}
