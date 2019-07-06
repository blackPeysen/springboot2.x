package com.org.peysen.bootcommon.Constant.enums;

import com.org.peysen.bootcommon.exception.BaseException;
import com.org.peysen.bootcommon.exception.assertion.CommonExceptionAssert;
import com.org.peysen.bootcommon.pojo.response.BaseResponse;

/**
 * @Description: java类作用描述
 * @Author: peimm
 * @CreateDate: 2019/7/6 08:27
 * @UpdateRemark: The modified content
 */
public enum CommonResponseEnum implements CommonExceptionAssert {
    /**
     * 成功
     */
    SUCCESS(0, "SUCCESS"),
    /**
     * 服务器繁忙，请稍后重试
     */
    SERVER_BUSY(9998, "服务器繁忙"),
    /**
     * 服务器异常，无法识别的异常，尽可能对通过判断减少未定义异常抛出
     */
    SERVER_ERROR(9999, "网络异常"),

    ;

    CommonResponseEnum() { }

    CommonResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 返回码
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;

    /**
     * 校验返回结果是否成功
     * @param response 远程调用的响应
     */
    public static void assertSuccess(BaseResponse response) {
        SERVER_ERROR.assertNotNull(response);
        int code = response.getCode();
        if (CommonResponseEnum.SUCCESS.getCode() != code) {
            String msg = response.getMessage();
            throw new BaseException(code, msg);
        }
    }

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
