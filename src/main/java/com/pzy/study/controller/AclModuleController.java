package com.pzy.study.controller;

import com.pzy.study.base.commons.enums.WebBaseExceptionEnum;
import com.pzy.study.base.commons.exceptions.WebBaseException;
import com.pzy.study.base.commons.utils.Result;
import com.pzy.study.entity.AclModuleEntity;
import com.pzy.study.entity.AclModuleLevelEntity;
import com.pzy.study.service.AclModuleService;
import com.pzy.study.vo.AclModuleVo;
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
 * Time: 2019-05-26
 */
@RestController
@RequestMapping("/aclModule")
public class AclModuleController {

    @Autowired
    private AclModuleService aclModuleService;

    @RequestMapping(value = "/saveAclModule" , method = RequestMethod.POST)
    public Result saveAclModule(@Valid AclModuleVo aclModuleVO , BindingResult bindingResult){
        aclModuleService.saveAclModule(aclModuleVO);
        return Result.success();
    }

    @RequestMapping(value = "/updateAclModule" ,method = RequestMethod.POST)
    public Result updateAclModule(@Valid AclModuleVo aclModuleVO , BindingResult bindingResult){
        aclModuleService.updateaclModule(aclModuleVO);
        return Result.success();
    }

    @RequestMapping(value = "/aclModuleTree" , method = RequestMethod.GET)
    public Result aclModuleTree(){
        List<AclModuleLevelEntity> aclModuleEntities = aclModuleService.aclModuleTree();
        return Result.success().set(Result.DATA_KEY , aclModuleEntities);
    }
}
