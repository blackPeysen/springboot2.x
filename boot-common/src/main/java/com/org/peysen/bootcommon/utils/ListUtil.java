package com.org.peysen.bootcommon.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : mengmeng.pei
 * @Date : 2020/2/26
 * @Desc :
 */
public class ListUtil {

    public static <T> List<List<T>> splitList(List<T> dataList, int pageSize){
        List<List<T>> lists = new ArrayList<>();

        if(dataList != null && pageSize != 0){
            int page = (dataList.size() + (pageSize -1)) / pageSize;

            for(int i=0;i<page;i++){
                int indexSta = i*pageSize;
                int indexEnd = (i+1)*pageSize;
                if(indexEnd > dataList.size()){
                    indexEnd = dataList.size();
                }
                lists.add(dataList.subList(indexSta,indexEnd));
            }
        }

        return lists;
    }
}
