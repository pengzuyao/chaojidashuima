package com.pzy.study.base.commons.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-23
 */
public class LevelUtil {

    public static final String SEPARATOR = ".";

    public static final String ROOT = "0";

    public static String calculateLevel(String parentLevel , int parnetId){
        if (StringUtils.isBlank(parentLevel)){
            return ROOT;
        }else {
            return StringUtils.join(parentLevel , SEPARATOR , parnetId);
        }
    }
}
