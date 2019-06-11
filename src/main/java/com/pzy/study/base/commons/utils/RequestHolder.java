package com.pzy.study.base.commons.utils;

import com.pzy.study.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-25
 */
public class RequestHolder {

    private static final ThreadLocal<UserEntity> userHolder = new ThreadLocal<UserEntity>();

    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();

    public static void add(UserEntity userEntity){
        userHolder.set(userEntity);
    }

    public static void add(HttpServletRequest request){
        requestHolder.set(request);
    }

    public static UserEntity getCurrentUser() {
        return userHolder.get();
    }

    public static HttpServletRequest getCurrentRequest() {
        return requestHolder.get();
    }

    public static void remove(){
        userHolder.remove();
        requestHolder.remove();
    }

}
