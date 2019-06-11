package com.pzy.study.base.commons.handlers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-20
 */

@Configuration
public class WebBaseExceptionHandler implements HandlerExceptionResolver {

    public static final String CODE = "500";
    public static final String ERROR_PATH = "error_path";
    public static final String ERROR_EXCEPTION = "error_exception";

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
            request.setAttribute(ERROR_PATH , request.getRequestURI());
            request.setAttribute(ERROR_EXCEPTION , ex);
            return null;
        /*if (ex instanceof WebBaseException){
            WebBaseException webBaseException = (WebBaseException) ex;
            WebBaseExceptionEnum webBaseExceptionEnum = webBaseException.getWebBaseExceptionEnum();
            String code = StringUtils.isBlank(webBaseExceptionEnum.getCode()) ? CODE : webBaseExceptionEnum.getCode();
            String message = StringUtils.isBlank(webBaseExceptionEnum.getDesc())? ex.getMessage() : webBaseExceptionEnum.getDesc();
            Result set = Result.ok().set("path", path).set("code", code).set("message", message);
            ModelAndView mv = new ModelAndView("error" ,set);
            return mv;
        }*/
    }
}
