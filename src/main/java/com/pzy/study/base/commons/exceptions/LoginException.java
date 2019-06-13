package com.pzy.study.base.commons.exceptions;

import com.pzy.study.base.commons.enums.LoginExceptionEnum;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-12
 */
public class LoginException extends RuntimeException {

    private LoginExceptionEnum loginExceptionEnum;

    public LoginExceptionEnum getLoginExceptionEnum() {
        return loginExceptionEnum;
    }

    public LoginException(LoginExceptionEnum loginExceptionEnum) {
        this.loginExceptionEnum = loginExceptionEnum;
    }

    public LoginException(String message, LoginExceptionEnum loginExceptionEnum) {
        super(message);
        this.loginExceptionEnum = loginExceptionEnum;
    }
}
