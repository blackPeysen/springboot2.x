package com.org.peysen.bootmvc.customized.converter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.Properties;

/**
 * @Description: 自定义数据类型转换器：将String 转换为 Properties
 * Created by mengmeng.Pei
 * 2019/8/8 9:25
 */

public class StringToPropertiesConverter implements Converter<String, Properties> {

    @Override
    public Properties convert(String source) {
        Properties properties = null;

        if (StringUtils.isBlank(source)){
            System.out.println("StringToPropertiesConverter,source:"+source);
            String[] split = source.split(",");
            String[] contexts = null;
            for(String context : split){
                contexts = context.split(":");
                properties.setProperty(contexts[0],contexts[1]);
            }
        }

        return properties;
    }
}
