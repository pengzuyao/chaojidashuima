package com.pzy.study.controller;

import com.pzy.study.base.commons.enums.WebBaseExceptionEnum;
import com.pzy.study.base.commons.exceptions.WebBaseException;
import com.pzy.study.base.commons.utils.Result;
import com.pzy.study.entity.DeptEntity;
import com.pzy.study.service.DeptService;
import com.pzy.study.vo.DeptVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-23
 */
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/saveDept" , method = RequestMethod.POST)
    public Result saveDept(@Valid DeptVo deptVO , BindingResult bindingResult){
        deptService.saveDept(deptVO);
        return Result.success();
    }

    @RequestMapping(value = "/deptTree" , method = RequestMethod.GET)
    public Result deptTree(){
        List<DeptEntity> list = deptService.deptTree();
        return Result.success().set("data" , list);
    }

    @RequestMapping(value = "/updateDept" ,method = RequestMethod.POST )
    public Result updateDept(DeptVo deptVO){
        deptService.updateDept(deptVO);
        return Result.success();
    }
}
