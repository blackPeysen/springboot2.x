package com.org.peysen.bootcommon.exception;

import com.org.peysen.bootcommon.Constant.enums.IResponseEnum;

/**
 * @Description: java类作用描述
 * @Author: peimm
 * @CreateDate: 2019/7/6 08:25
 * @UpdateRemark: The modified content
 */
public class ValidationException extends  BaseException {

    private static final long serialVersionUID = 1L;

    public ValidationException(IResponseEnum responseEnum, Object[] args, String message) {
        super(responseEnum, args, message);
    }

    public ValidationException(IResponseEnum responseEnum, Object[] args, String message, Throwable cause) {
        super(responseEnum, args, message, cause);
    }
}
