package com.org.peysen.bootmvc.paramConverter;

import com.alibaba.fastjson.JSON;
import com.org.peysen.bootmvc.entity.OrderDetail;
import org.springframework.core.convert.converter.Converter;
import java.util.List;

/**
 * @Description: java类作用描述
 * Created by mengmeng.Pei
 * 2019/8/8 9:33
 */
public class StringToOrderConverter implements Converter<String, List<OrderDetail>> {

    @Override
    public List<OrderDetail> convert(String json) {
        System.out.println("StringToOrderConverter convert:" + json);
        List<OrderDetail> priceDetails = JSON.parseArray(json,OrderDetail.class);
        return  priceDetails;
    }
}
