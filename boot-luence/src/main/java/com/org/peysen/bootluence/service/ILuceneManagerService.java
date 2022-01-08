package com.org.peysen.bootluence.service;

import java.io.IOException;

/**
 * Author: peimengmeng
 * Date: 2022/1/4 9:35
 * Desc:
 */
public interface ILuceneManagerService {

    void createIndex(Long goodsId);

    /**
     * 小程序项目启动后将同步Product表,并创建index
     * 全量更新商品索引
     * @throws IOException
     */
    void synProductCreatIndex(Long busiId) throws IOException;

    /**
     * 更新一个索引
     *
     * @param goodsId
     * @throws IOException
     */
    void updateProductIndex(Long goodsId) throws IOException;

    /**
     * 通过id删除商品索引
     * @param id
     * @throws IOException
     */
    void deleteProductIndexById(String id) throws IOException;

    /**
     * 删除所有的商品索引
     */
    void deleleAllProduct() throws IOException;

}
