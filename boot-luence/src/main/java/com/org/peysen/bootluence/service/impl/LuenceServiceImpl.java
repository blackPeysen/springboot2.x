package com.org.peysen.bootluence.service.impl;

import com.org.peysen.bootluence.service.ILuenceService;
import com.org.peysen.bootluence.util.LuenceOperatorUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * Author: peimengmeng
 * Date: 2022/1/4 9:36
 * Desc:
 */
@Service
public class LuenceServiceImpl implements ILuenceService {
    @Value("${luence.index.dir}")
    private String indexDir;

    @Override
    public void createIndex() {
        try {
            LuenceOperatorUtil.createIndex(indexDir, new File("E:\\documents\\报销文档.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
