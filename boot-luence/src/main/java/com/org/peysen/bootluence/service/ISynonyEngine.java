package com.org.peysen.bootluence.service;

import java.io.IOException;
import java.util.List;

/**
 * Author: peimengmeng
 * Date: 2022/1/13 11:30
 * Desc:
 */
public interface ISynonyEngine {

    List<String> getSynonyWords(String s) throws IOException;

}
