package com.org.peysen.bootcommon.Constant.enums;

import com.org.peysen.bootcommon.exception.assertion.CommonExceptionAssert;

/**
 * @Description: java类作用描述
 * @Author: peimm
 * @CreateDate: 2019/7/6 08:26
 * @UpdateRemark: The modified content
 */
public enum  ArgumentResponseEnum implements CommonExceptionAssert {
    /**
     * 绑定参数校验异常
     */
    VALID_ERROR(6000, "参数校验异常");

    /**
     * 返回码
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;

    ArgumentResponseEnum() {
    }

    ArgumentResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
