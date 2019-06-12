package com.pzy.study.base.commons.exceptions;

import com.pzy.study.base.commons.enums.WebBaseExceptionEnum;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-19
 */
public class WebBaseException extends RuntimeException {

    private WebBaseExceptionEnum webBaseExceptionEnum;

    public WebBaseException(String message, WebBaseExceptionEnum webBaseExceptionEnum) {
        super(message);
        this.webBaseExceptionEnum = webBaseExceptionEnum;
    }

    public WebBaseException(WebBaseExceptionEnum webBaseExceptionEnum) {
        this.webBaseExceptionEnum = webBaseExceptionEnum;
    }

    public WebBaseExceptionEnum getWebBaseExceptionEnum() {
        return webBaseExceptionEnum;
    }

}
