package com.pzy.study.controller;

import com.pzy.study.base.commons.enums.WebBaseExceptionEnum;
import com.pzy.study.base.commons.exceptions.WebBaseException;
import com.pzy.study.base.commons.utils.Result;
import com.pzy.study.service.AclService;
import com.pzy.study.vo.AclVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-27
 */
@RestController
@RequestMapping("/acl")
public class AclController {

    @Autowired
    private AclService aclService;

      @RequestMapping(value = "/aclSave" , method = RequestMethod.POST)
      public Result  saveAcl(@Valid AclVo aclVO , BindingResult bindingResult){
          aclService.saveAcl(aclVO);
          return Result.success();
      }

      @RequestMapping(value = "/aclUpdate" , method = RequestMethod.POST)
      public Result updateAcl(@Valid AclVo aclVO , BindingResult bindingResult){
          aclService.update(aclVO);
          return Result.success();
      }

      @RequestMapping(value = "/aclPageQuery" , method = RequestMethod.GET)
      public Result aclPageQuery(@Valid AclVo aclVO , BindingResult bindingResult){
         return aclService.userPageQuery(aclVO);
      }
}
