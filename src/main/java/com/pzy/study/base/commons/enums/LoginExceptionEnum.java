package com.pzy.study.base.commons.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-12
 */
public enum  LoginExceptionEnum {

    request_login("400" , "请登录"),
    request_prarm("401" , "用户名或密码为空"),
    request_unVaild("402" , "用户名密码不匹配"),
    request_overtime("403" , "超时，请重新登录"),
    request_noRoles("404" , "角色信息为空"),
    request_noAcls("405" , "权限信息为空"),
    request_noUser("406 " , "用户信息不存在");



    private String code;
    private String desc;

    LoginExceptionEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public String findCodeByDesc(String desc){
        for (LoginExceptionEnum anEnum : LoginExceptionEnum.values()) {
            if (StringUtils.equals(desc ,anEnum.getDesc())){
                return anEnum.code;
            }
        }
        return null;
    }

    public String findDescByCode(String code){
        for (LoginExceptionEnum anEnum : LoginExceptionEnum.values()) {
            if (StringUtils.equals(code ,anEnum.getCode())){
                return anEnum.desc;
            }
        }
        return null;
    }
}
