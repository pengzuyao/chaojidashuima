package com.pzy.study.base.commons.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-19
 */
public enum WebBaseExceptionEnum {

    request_prarms("404","请求参数不合法");

    private String code;
    private String desc;


    WebBaseExceptionEnum(String code , String desc){
        this.code = code;
        this.desc = desc;
    };

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

   public String findCodeByDesc(String desc){
        for (WebBaseExceptionEnum anEnum : WebBaseExceptionEnum.values()) {
            if (StringUtils.equals(desc ,anEnum.getDesc())){
                return anEnum.code;
            }
        }
        return null;
    }

    public String findDescByCode(String code){
        for (WebBaseExceptionEnum anEnum : WebBaseExceptionEnum.values()) {
            if (StringUtils.equals(code ,anEnum.getCode())){
                return anEnum.desc;
            }
        }
        return null;
    }
}
