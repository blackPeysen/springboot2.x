package com.org.peysen.bootcommon.exception.assertion;

import com.org.peysen.bootcommon.exception.BaseException;
import com.org.peysen.bootcommon.exception.BusinessException;
import com.org.peysen.bootcommon.Constant.enums.IResponseEnum;

import java.text.MessageFormat;

/**
 * @Description: java类作用描述
 * @Author: peimm
 * @CreateDate: 2019/7/6 08:21
 * @UpdateRemark: The modified content
 */
public interface BusinessExceptionAssert extends IResponseEnum, Assert {

    @Override
    default BaseException newException(Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);

        return new BusinessException(this, args, msg);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);

        return new BusinessException(this, args, msg, t);
    }
}
