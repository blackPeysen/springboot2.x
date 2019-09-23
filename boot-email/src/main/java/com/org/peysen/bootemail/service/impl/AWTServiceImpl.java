package com.org.peysen.bootemail.service.impl;

import com.org.peysen.bootemail.service.ThumbnailService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Description: AWT图片缩略处理
 * Created by mengmeng.Pei
 * 2019/9/23 14:12
 */
@Service("aWTServiceImpl")
public class AWTServiceImpl implements ThumbnailService {
    public static final int WIDTH=100;
    public static final int HEIGHT=100;

    @Override
    public String thumbnail(CommonsMultipartFile file, String uploadPath, String realUploadPath){
        OutputStream oStream=null;
        try {
            String des = realUploadPath+"/thum_"+file.getOriginalFilename();
            oStream = new FileOutputStream(des);
            //ImageIO获取图片流信息
            Image image = ImageIO.read(file.getInputStream());
            int width = image.getWidth(null); //获取原图宽度
            int height = image.getHeight(null);//获取原图高度
            int wrate = width/WIDTH;      //宽度缩略图
            int hrate = height/HEIGHT;    //高度缩略图
            int rate = 0;

            //宽度缩略图比例大于高度缩略图，使用宽度缩略图
            if (wrate > hrate) {
                rate = wrate;
            } else {
                rate = hrate;
            }
            //计算缩略图最终的宽度和高度
            int newWidth = width/rate;
            int newHeight = height/rate;

            BufferedImage bufferedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            //图片缩略图实现
            bufferedImage.getGraphics().drawImage(
                    image.getScaledInstance(newWidth, newHeight, image.SCALE_SMOOTH), 0, 0, null);
            //*image/jpeg
            String imageType = file.getContentType().substring(file.getContentType().indexOf("/")+1);
            ImageIO.write(bufferedImage, imageType, oStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                oStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //缩略图返回的相对路径
        return uploadPath + "/thum_" + file.getOriginalFilename();
    }
}
