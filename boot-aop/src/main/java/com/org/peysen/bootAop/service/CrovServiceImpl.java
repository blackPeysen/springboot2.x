package com.org.peysen.bootAop.service;

import com.org.peysen.bootAop.annotation.CrovRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auther: peimengmeng
 * @Date: 2021/7/17_08:26
 * @Desc:
 */
@Component
public class CrovServiceImpl {
    @Autowired
    private CrovRpcServiceImpl crovRpcService;

    public void invoke(){
        String s = crovRpcService.remoteRpcInvoke();

        System.out.println("result:" + s);
    }

}
