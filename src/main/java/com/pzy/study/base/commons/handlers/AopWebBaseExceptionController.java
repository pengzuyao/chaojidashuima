package com.pzy.study.base.commons.handlers;

import com.pzy.study.base.commons.enums.WebBaseExceptionEnum;
import com.pzy.study.base.commons.exceptions.WebBaseException;
import com.pzy.study.base.commons.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-21
 */
@ControllerAdvice
@RestController
@Order(100)
@Slf4j
public class AopWebBaseExceptionController {

    public static final String CODE = "500";
    public static final String SYSTEM_ERROR = "SYSTEM ERROR";

    @ExceptionHandler(Exception.class)
    public Result errorHandler(Exception e, HttpServletRequest request , HttpServletResponse response){
            e.printStackTrace();
        if (e instanceof WebBaseException){
            WebBaseException webBaseException = (WebBaseException) e;
            WebBaseExceptionEnum webBaseExceptionEnum = webBaseException.getWebBaseExceptionEnum();
            String code = StringUtils.isBlank(webBaseExceptionEnum.getCode()) ? CODE : webBaseExceptionEnum.getCode();
            String message = StringUtils.isNotBlank(e.getMessage())? e.getMessage() : webBaseExceptionEnum.getDesc();
            return Result.ok().set("path", request.getRequestURI()).set("code", code).set("message", message);
        }else {
            String message = StringUtils.isNotBlank(e.getMessage())? e.getMessage() :SYSTEM_ERROR;
            return Result.ok().set("path", request.getRequestURI()).set("code",response.getStatus()).set("message", message);
        }
    }
}
