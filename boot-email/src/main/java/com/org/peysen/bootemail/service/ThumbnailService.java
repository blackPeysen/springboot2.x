package com.org.peysen.bootemail.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @Description: java类作用描述
 * Created by mengmeng.Pei
 * 2019/9/23 14:14
 */
public interface ThumbnailService {
    String thumbnail(CommonsMultipartFile file, String uploadPath, String realUploadPath);
}
