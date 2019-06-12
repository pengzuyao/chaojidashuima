package com.pzy.study.controller;

import com.github.pagehelper.PageInfo;
import com.pzy.study.base.commons.enums.WebBaseExceptionEnum;
import com.pzy.study.base.commons.exceptions.WebBaseException;
import com.pzy.study.base.commons.utils.Result;
import com.pzy.study.entity.UserEntity;
import com.pzy.study.service.UserService;
import com.pzy.study.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-19
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/saveUser" ,method = RequestMethod.POST)
    public Result save(@Valid  UserVo userVO , BindingResult bindingResult){
        userService.save(userVO);
        return Result.success();
    }

    @RequestMapping(value = "/updateUser" , method = RequestMethod.POST)
    public Result update(@Valid UserVo userVO , BindingResult bindingResult){
        return Result.success();
    }

    @RequestMapping(value = "/getUserInfo" , method = RequestMethod.GET)
    public UserEntity getUserInfo(@RequestParam("uname") String uname){
        return userService.findUserInfoByName(uname);
    }

    @RequestMapping(value = "/userPageQuery" ,method = RequestMethod.GET)
    public Result userPageQuery(UserVo userVo){
        return userService.userPageQuery(userVo);
    }

    @RequestMapping(value = "changeUsers" , method = RequestMethod.POST)
    public Result changeUsers(@RequestParam(value = "roleId" , required = true)Integer roleId , @RequestParam("userIds")String userIds){
            return null;
    }
}
