package com.org.peysen.bootcommon.exception;

import com.org.peysen.bootcommon.Constant.enums.IResponseEnum;

/**
 * @Description: java类作用描述
 * @Author: peimm
 * @CreateDate: 2019/7/6 08:23
 * @UpdateRemark: The modified content
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 返回码
     */
    protected IResponseEnum responseEnum;
    /**
     * 异常消息参数
     */
    protected Object[] args;

    public BaseException(IResponseEnum responseEnum) {
        super(responseEnum.getMessage());
        this.responseEnum = responseEnum;
    }

    public BaseException(int code, String msg) {
        super(msg);
        this.responseEnum = new IResponseEnum() {
            @Override
            public int getCode() {
                return code;
            }

            @Override
            public String getMessage() {
                return msg;
            }
        };
    }

    public BaseException(IResponseEnum responseEnum, Object[] args, String message) {
        super(message);
        this.responseEnum = responseEnum;
        this.args = args;
    }

    public BaseException(IResponseEnum responseEnum, Object[] args, String message, Throwable cause) {
        super(message, cause);
        this.responseEnum = responseEnum;
        this.args = args;
    }

    public IResponseEnum getResponseEnum() {
        return responseEnum;
    }

    public void setResponseEnum(IResponseEnum responseEnum) {
        this.responseEnum = responseEnum;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
