package com.org.peysen.bootcommon.Constant.enums;

/**
 * @Description: java类作用描述
 * @Author: peimm
 * @CreateDate: 2019/7/6 08:29
 * @UpdateRemark: The modified content
 */
public interface IResponseEnum {
    /**
     * 获取返回码
     * @return 返回码
     */
    int getCode();

    /**
     * 获取返回信息
     * @return 返回信息
     */
    String getMessage();

}
