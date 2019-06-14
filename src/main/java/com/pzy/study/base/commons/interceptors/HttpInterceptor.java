package com.pzy.study.base.commons.interceptors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.pzy.study.base.commons.constants.GlobalConstant;
import com.pzy.study.base.commons.enums.LoginExceptionEnum;
import com.pzy.study.base.commons.exceptions.LoginException;
import com.pzy.study.base.commons.utils.AESUtil;
import com.pzy.study.base.commons.utils.RedisUtil;
import com.pzy.study.base.commons.utils.RequestHolder;
import com.pzy.study.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
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

    @Value("aes.key")
    private String key;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        Map<String, String[]> parameterMap = request.getParameterMap();
        log.info("request-start【 url:{} , params:{} 】" ,url , JSON.toJSONString(parameterMap));
        request.setAttribute(GlobalConstant.START_TIME,System.currentTimeMillis());
        String token =(String)request.getSession().getAttribute(GlobalConstant.USER_TOKEN);
        UserEntity userEntity = null;
        try {
            String decrypt = AESUtil.decrypt(key, token);
            Map<String , Object> map = JSONObject.parseObject(decrypt, Map.class);
            Object object =  map.get(GlobalConstant.USER_NAME);
            userEntity = JSONObject.parseObject(JSON.toJSONString(object), UserEntity.class);
        }catch (Exception e){
            log.error("解密失败：{}" , e.getMessage());
            response.sendRedirect("/water-cup/login");
            return false;
        }
        /*String currentToken = request.getHeader(GlobalConstant.USER_TOKEN);
        if (StringUtils.isBlank(currentToken)){
            log.info("缺少请求头信息");
            response.sendRedirect("/water-cup/login");
            return false;
        }
        UserEntity userEntity = null;
        try {
            String decrypt = AESUtil.decrypt(key, currentToken);
            userEntity = JSONObject.parseObject(decrypt, UserEntity.class);
        }catch (Exception e){
            log.error("解密失败：{}" , e.getMessage());
            response.sendRedirect("/water-cup/login");
            return false;
        }
        String token =(String)redisUtil.get(userEntity.getUsername());
        if (token == null && !currentToken.equals(token)){
            log.info("登录超时或账号已在其它地方登录");
            response.sendRedirect("/water-cup/login");
            return false;
        }*/
        RequestHolder.add(userEntity);
        RequestHolder.add(request);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        /*Cookie cookieName = new Cookie(GlobalConstant.USER_NAME , RequestHolder.getCurrentUser().getUsername());
        Object object = redisUtil.get(RequestHolder.getCurrentUser().getUsername());
        Cookie cookieToken = new Cookie(GlobalConstant.USER_TOKEN , object== null? null: (String)object);
        response.addCookie(cookieName);
        response.addCookie(cookieToken);*/
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        removeCurrentThreadLocalInfo();
        long startTime = (long) request.getAttribute(GlobalConstant.START_TIME);
        log.info("request---end【 time:{}ms 】" , System.currentTimeMillis() - startTime);
    }

    private static void removeCurrentThreadLocalInfo(){
        RequestHolder.remove();
    }

}
