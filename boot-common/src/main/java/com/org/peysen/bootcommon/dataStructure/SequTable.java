package com.org.peysen.bootcommon.dataStructure;

/**
 * @Author : mengmeng.pei
 * @Date : 2020/2/13
 * @Desc : 顺序表操作实例
 */
public class SequTable {

    //初始化顺序表
    void initTable(SLType slType){
        slType.listLen = 0;
    }

    //获取顺序表的元素数量
    int SLLength(SLType slType){
        return slType.listLen;
    }

    //向顺序表指定位置上插入元素
    int SLInsert(SLType slType, int n, Data data){
        int i;
        //判断当前顺序表中长度是否超过最大数量
        if(slType.listLen > SLType.MAX_SIZE){
            System.out.println("顺序表已满，不能插入节点");
            return 0;
        }
        //判断n是否合理
        if (n > 1 && n < SLType.MAX_SIZE){
            //将n之后的元素往后移动
            for (i =n; i<slType.listLen;i++){
                slType.listData[i++] = slType.listData[i];
            }
            slType.listData[n] = data;
            slType.listLen++;
        }else{
            System.out.println("插入元素序号有误，不能插入元素");
            return 0;
        }

        return 1;
    }

    //增加一个元素到顺序表尾部
    int SLAdd(SLType slType, Data data){
        if(slType.listLen > SLType.MAX_SIZE){
            System.out.println("顺序表已满，不能插入节点");
            return 0;
        }
        slType.listData[slType.listLen++] = data;
        return 1;
    }

    //删除顺序表中指定位置上的元素
    int SLDelete(SLType slType, int n){
        //判断n是否合理
        if (n > 1 && n < SLType.MAX_SIZE){
            //将n之后的元素往前移动
            for (int i = n; i<slType.listLen;i++){
                slType.listData[i] = slType.listData[i+1];
            }
            slType.listLen--;
        }else{
            System.out.println("删除元素序号有误，不能删除元素");
            return 0;
        }

        return  1;
    }

    Data SLFindByNum(SLType slType, int n){
        if (n < 1 || n > slType.listLen+1){
            System.out.println("元素序号有误，不能返回节点");
            return null;
        }
        return slType.listData[n];
    }

    Data SLFindByKey(SLType slType, String key){
        for (int i=0; i<slType.listLen;i++){
            if(slType.listData[0].key.compareTo(key) == 0){
                return slType.listData[i];
            }
        }
        return null;
    }

    @lombok.Data
    class Data{
        String key;
        String name;
        int age;
    }

    class SLType{
        static final int MAX_SIZE = 100;
        Data[] listData = new Data[MAX_SIZE];
        int listLen;
    }
}





