package com.org.peysen.bootluence.service.impl;

import com.google.common.collect.Lists;
import com.org.peysen.bootluence.service.ISynonyEngine;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: peimengmeng
 * Date: 2022/1/13 11:30
 * Desc:
 */
public class SynonyEngineImpl implements ISynonyEngine {
    private static Map<String, List<String>> synonyMap = new HashMap<>();

    static {
        synonyMap.put("quick", Lists.newArrayList("fast", "speedy"));
        synonyMap.put("dog", Lists.newArrayList("canine", "pooch"));
        synonyMap.put("amazon", Lists.newArrayList("peysen", "xian"));
        synonyMap.put("peysen", Lists.newArrayList("amazon", "xian"));
    }

    @Override
    public List<String> getSynonyWords(String s) throws IOException {
        return synonyMap.get(s);
    }
}
