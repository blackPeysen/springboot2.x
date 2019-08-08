package com.org.peysen.bootmvc.converter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 将String 转换为 Date
 * Created by mengmeng.Pei
 * 2019/8/8 9:25
 */


public class StringToDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        Date date = null;
        SimpleDateFormat simpleDateFormat = null;

        try {
            if (StringUtils.isNotBlank(source)){
                System.out.println("StringToDateConverter convert:" + source);
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                date = simpleDateFormat.parse(source);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}
