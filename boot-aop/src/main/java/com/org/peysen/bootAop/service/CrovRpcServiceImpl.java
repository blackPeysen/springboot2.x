package com.org.peysen.bootAop.service;

import com.org.peysen.bootAop.annotation.CrovRpc;
import org.springframework.stereotype.Component;

/**
 * @Auther: peimengmeng
 * @Date: 2021/7/17_08:26
 * @Desc:
 */
@CrovRpc
@Component
public class CrovRpcServiceImpl {

    public String remoteRpcInvoke(int a){
        System.out.println("这是一个rpc远程调用接口。。。");

        return "这是一个rpc远程调用接口。。。";
    }

    public static  String remoteRpcInvoke1(){
        System.out.println("这是一个rpc远程调用接口1。。。");

        return "这是一个rpc远程调用接口1。。。";
    }

    protected  String remoteRpcInvoke2(){
        System.out.println("这是一个rpc远程调用接口2。。。");

        return "这是一个rpc远程调用接口2。。。";
    }

    String remoteRpcInvoke3(){
        System.out.println("这是一个rpc远程调用接口3。。。");

        return "这是一个rpc远程调用接口3。。。";
    }

    private String remoteRpcInvoke4(){
        System.out.println("这是一个rpc远程调用接口4。。。");

        return "这是一个rpc远程调用接口4。。。";
    }
}
