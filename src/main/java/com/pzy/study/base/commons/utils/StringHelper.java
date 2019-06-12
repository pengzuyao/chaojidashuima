package com.pzy.study.base.commons.utils;

import com.pzy.study.base.commons.enums.WebBaseExceptionEnum;
import com.pzy.study.base.commons.exceptions.WebBaseException;

import java.util.List;

/*
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/06/12
 */
public class StringHelper {


    public static void stringToIntegerList(String str, List<Integer> List) {
        try {
            String[] split = str.split(",");
            for (int i = 0; i < split.length; i++) {
                List.add(Integer.valueOf(split[i]));
            }
        }catch (Exception e){
            throw  new WebBaseException("字符串转换异常" , WebBaseExceptionEnum.request_prarms);
        }

    }
}
