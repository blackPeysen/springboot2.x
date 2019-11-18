package com.org.peysen.bootemail.service.impl;

/**
 * @Description: java类作用描述
 * Created by mengmeng.Pei
 * 2019/9/23 14:10
 */

import com.org.peysen.bootemail.service.ThumbnailService;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.InputStream;

/**
 * Thumbnails图片缩略处理
 */
@Service("thumbnailServiceImpl")
public class ThumbnailServiceImpl implements ThumbnailService {

    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;

    @Override
    public String thumbnail(CommonsMultipartFile file, String uploadPath, String realUploadPath) {
        try {
            String des = realUploadPath + "/thum_" + file.getOriginalFilename();
            //图片缩略图实现，强制按照指定的宽高进行缩略keepAspectRatio(false)
            //方式一
            //Thumbnails.of(file.getInputStream()).size(WIDTH, HEIGHT).toFile(des);

            //方式二
            Thumbnails.Builder<? extends InputStream> thumbnail = Thumbnails.of(file.getInputStream());
            thumbnail.size(WIDTH, HEIGHT);
            thumbnail.toFile(des);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //缩略图返回的相对路径
        return uploadPath + "/thum_" + file.getOriginalFilename();
    }
}
