package com.org.peysen.bootrabbitmq.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @Description: FastJsonUtil
 * @Author: peysen
 * @CreateDate: 2019/7/20 08:58
 * @UpdateRemark: The modified content
 */
public class FastJsonUtil {


    /**
     * 将JavaBean转换为JSON对象
     */
    public static String beanToJSONObject(Object object) {
        JSONObject json = (JSONObject) JSON.toJSON(object);

        return  json.toJSONString();
    }


    /**
     * 将String转换为JavaBean
     */
    public static Object jsonToObject(String jsonStr,Class object) {
        return JSON.parseObject(jsonStr,object);
    }


}
