package com.pzy.study.controller;

import com.pzy.study.base.commons.utils.Result;
import com.pzy.study.entity.AclModuleLevelEntity;
import com.pzy.study.entity.UserEntity;
import com.pzy.study.service.RoleService;
import com.pzy.study.vo.RoleVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-03
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "roleSave" ,method = RequestMethod.POST)
    public Result roleSave(RoleVo roleVo){
        roleService.insertRole(roleVo);
        return Result.success();
    }

    @RequestMapping(value = "roleUpdate")
    public Result roleUpdate(RoleVo roleVo){
        roleService.updateRole(roleVo);
        return Result.success();
    }

    @RequestMapping("/rolelist")
    public Result list() {
        return Result.success(Result.DATA_KEY, roleService.getAll());
    }

    @RequestMapping(value = "/roleAclTree" , method = RequestMethod.GET)
    public Result roleAclTree(@Param(value = "roleId") Integer roleId){
        List<AclModuleLevelEntity> aclModuleLevelEntities = roleService.roleAclTree(roleId);
        return Result.success().set(Result.DATA_KEY,aclModuleLevelEntities);
    }

    @RequestMapping(value = "roleUsers" ,method = RequestMethod.POST)
    public Result RoleUser(@Param(value = "roleId") Integer roleId){
        Map<String, List<UserEntity>> stringListMap = roleService.roleUsers(roleId);
        return Result.success().set(Result.DATA_KEY , stringListMap);
    }
}
