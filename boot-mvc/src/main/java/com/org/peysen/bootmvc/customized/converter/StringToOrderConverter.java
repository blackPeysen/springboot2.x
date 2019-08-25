package com.org.peysen.bootmvc.customized.converter;

import com.alibaba.fastjson.JSON;
import com.org.peysen.bootmvc.entity.OrderDetail;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 自定义数据类型转换器：String--》List<OrderDetail>
 * Created by mengmeng.Pei
 * 2019/8/8 9:33
 */
@Component
public class StringToOrderConverter implements Converter<String, List<OrderDetail>> {

    @Override
    public List<OrderDetail> convert(String json) {
        System.out.println("StringToOrderConverter convert:" + json);
        List<OrderDetail> priceDetails = JSON.parseArray(json,OrderDetail.class);
        return  priceDetails;
    }

    public static void main(String[] args) {
        String str="[{\"productId\":\"1\",\"productName\":\"book1\",\"buyTime\":\"2019-08-08 09:38:34\"},{\"productId\":\"2\",\"productName\":\"book2\",\"buyTime\":\"2019-08-08 09:38:34\"}]";
        List<OrderDetail> priceDetails = JSON.parseArray(str,OrderDetail.class);
    }
}
