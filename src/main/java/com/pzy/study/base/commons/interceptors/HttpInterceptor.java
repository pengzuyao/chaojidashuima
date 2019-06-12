package com.pzy.study.base.commons.interceptors;

import com.alibaba.fastjson.JSON;
import com.pzy.study.base.commons.utils.RequestHolder;
import com.pzy.study.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-21
 */
@Slf4j
public class HttpInterceptor implements HandlerInterceptor {

    private static final String START_TIME = "start_time";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        Map<String, String[]> parameterMap = request.getParameterMap();
        log.info("request-start【 url:{} , params:{} 】" ,url , JSON.toJSONString(parameterMap));
        request.setAttribute(START_TIME ,System.currentTimeMillis());
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("user");
        if (null == userEntity){
            log.info("回话超时，请重新登录！");
            return false;
        }
        RequestHolder.add(userEntity);
        RequestHolder.add(request);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long startTime = (long) request.getAttribute(START_TIME);
        log.info("request---end【 time:{}ms 】" , System.currentTimeMillis() - startTime);
        removeCurrentThreadLocalInfo();
    }

    private static void removeCurrentThreadLocalInfo(){
        RequestHolder.remove();
    }
}
