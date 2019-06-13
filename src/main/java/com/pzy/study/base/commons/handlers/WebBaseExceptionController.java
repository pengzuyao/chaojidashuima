package com.pzy.study.base.commons.handlers;

import com.pzy.study.base.commons.enums.WebBaseExceptionEnum;
import com.pzy.study.base.commons.exceptions.LoginException;
import com.pzy.study.base.commons.exceptions.WebBaseException;
import com.pzy.study.base.commons.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-21
 */
/*@Controller
public class WebBaseExceptionController implements ErrorController {

    private static final String PATH = "/error";
    public static final String SYSTEM_ERROR = "SYSTEM ERROR";

    @RequestMapping(value = PATH)
    public String errorHandler(HttpServletRequest request, HttpServletResponse response ){
        String path = (String)request.getAttribute(WebBaseExceptionHandler.ERROR_PATH);
        Exception e = (Exception) request.getAttribute(WebBaseExceptionHandler.ERROR_EXCEPTION);
         if (e instanceof LoginException){
            return "redirect:/water-cup/login";
        }else {
             return "exception";
         }
    }


    @Override
    public String getErrorPath() {
        return PATH;
    }



}*/
