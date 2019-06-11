package com.pzy.study.controller;

import com.pzy.study.base.commons.utils.Result;
import com.pzy.study.vo.LogVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-09
 */
@RestController
@RequestMapping("/log")
public class logController {

    @RequestMapping(value = "/logPageQuery" ,method = RequestMethod.POST)
    public Result logPageQuery(@Valid LogVo logVo ){
        return Result.success().set(Result.DATA_KEY , null);
    }

}
