package com.org.peysen.bootemail.service;

import com.org.peysen.bootemail.utils.ZxingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: java类作用描述
 * Created by mengmeng.Pei
 * 2019/9/23 11:00
 */

@Service
public class QRCodeService {

    @Autowired
    private ZxingUtil zxingUtil;

    public String crateQRCode(String content, int width, int height){
        boolean b = zxingUtil.zxingCodeCreate(content, "D:", height, "/static/static/yw.png");
        return "sdf";
    }

}
