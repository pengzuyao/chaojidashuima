package com.pzy.study.base.commons.handlers;

import com.pzy.study.base.commons.enums.WebBaseExceptionEnum;
import com.pzy.study.base.commons.exceptions.WebBaseException;
import com.pzy.study.base.commons.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-21
 */
@RestController
public class WebBaseExceptionController implements ErrorController {

    private static final String PATH = "/error";
    public static final String SYSTEM_ERROR = "SYSTEM ERROR";

    @RequestMapping(value = PATH)
    public Result errorHandler(HttpServletRequest request, HttpServletResponse response){
        String path = (String)request.getAttribute(WebBaseExceptionHandler.ERROR_PATH);
        Exception e = (Exception) request.getAttribute(WebBaseExceptionHandler.ERROR_EXCEPTION);
        if (null == e){
            return null;
        }
        String message = StringUtils.isNotBlank(e.getMessage())? e.getMessage() : SYSTEM_ERROR;
        Result result = Result.ok().set("path", path).set("code", WebBaseExceptionHandler.CODE).set("message", message);
        return result;
    }


    @Override
    public String getErrorPath() {
        return PATH;
    }
}
