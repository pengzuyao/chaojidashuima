package com.pzy.study.base.commons.handlers;

import com.pzy.study.base.commons.enums.WebBaseExceptionEnum;
import com.pzy.study.base.commons.exceptions.WebBaseException;
import com.pzy.study.base.commons.utils.Result;
import org.apache.commons.lang3.StringUtils;
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

/*@Configuration
public class WebBaseExceptionHandler implements HandlerExceptionResolver {

    public static final String CODE = "500";
    public static final String ERROR_PATH = "error_path";
    public static final String ERROR_EXCEPTION = "error_exception";

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
            request.setAttribute(ERROR_PATH , request.getRequestURI());
            request.setAttribute(ERROR_EXCEPTION , ex);
            return null;
    }
}*/
