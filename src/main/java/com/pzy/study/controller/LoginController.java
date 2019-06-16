package com.pzy.study.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.pzy.study.base.commons.constants.GlobalConstant;
import com.pzy.study.base.commons.enums.LoginExceptionEnum;
import com.pzy.study.base.commons.exceptions.LoginException;
import com.pzy.study.base.commons.utils.*;
import com.pzy.study.entity.AclEntity;
import com.pzy.study.entity.RoleEntity;
import com.pzy.study.entity.UserEntity;
import com.pzy.study.service.AclService;
import com.pzy.study.service.RoleService;
import com.pzy.study.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-19
 */
@Controller
@RequestMapping("/water-cup")
@Slf4j
public class LoginController {

    @Value("aes.key")
    private String key;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AclService aclService;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/sys/login")
    public void login(@Valid UserEntity userEntity , HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult)throws IOException{
        if (StringUtils.isAnyBlank(userEntity.getUsername() ,userEntity.getPassword())){
            throw new LoginException(LoginExceptionEnum.request_prarm);
        }
        UserEntity userInfo = userService.findUserInfoByName(userEntity.getUsername());
        if (userInfo == null){
            throw new LoginException(LoginExceptionEnum.request_noUser);
        }
        String encryptPassword = PasswordHelper.encryptAndGetPassword(userInfo.getSalt(), userEntity.getPassword());
        if (!userInfo.getPassword().equals(encryptPassword)){
            throw new LoginException(LoginExceptionEnum.request_unVaild);
        }
        List<RoleEntity> rolesByUserId = roleService.findRolesByUserId(userInfo.getId());
        redisUtil.set(userEntity.getUsername() +":userRoles" , rolesByUserId);
        //List<RoleEntity> roleEntities = (List<RoleEntity>) redisUtil.get(userEntity.getUsername() + ":userRoles");
        //log.info("redis用户角色信息：{}" ,roleEntities);
        if (rolesByUserId.isEmpty()){
            throw new LoginException(LoginExceptionEnum.request_noRoles);
        }
        List<Integer> collect = rolesByUserId.stream().map(roleEntity -> roleEntity.getId()).distinct().collect(Collectors.toList());
        List<AclEntity> aclsByRoleIds = aclService.findAclsByRoleIds(collect);
        if (aclsByRoleIds.isEmpty()){
            throw new LoginException(LoginExceptionEnum.request_noAcls);
        }
        //登录成功
        long time = System.currentTimeMillis();
        Map<String ,Object> map = Maps.newHashMap();
        map.put(GlobalConstant.TIMESTAMP,time);
        map.put(GlobalConstant.USER_NAME , userInfo);
        String content = JSON.toJSONString(map);
        String token = AESUtil.encrypt(key, content);
        //redisUtil.del(userEntity.getUsername());
        //redisUtil.expire(userEntity.getUsername() , token ,1800);
        request.getSession().setAttribute(GlobalConstant.USER_TOKEN ,token);
        request.getSession().setMaxInactiveInterval(1800);
        log.info("登录token信息：{}" ,token);
        Cookie cookieName = new Cookie(GlobalConstant.USER_NAME , userEntity.getUsername());
        Cookie cookieToken = new Cookie(GlobalConstant.USER_TOKEN ,token);
        response.addCookie(cookieName);
        response.addCookie(cookieToken);
        response.sendRedirect("/water-cup/main");
    }

    @RequestMapping(value = "/main" ,method = RequestMethod.GET)
    public String main(){
        return "admin";
    }

    @RequestMapping(value = "/login" ,method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/logout" ,method = RequestMethod.GET)
    public void logout(HttpServletRequest request ,HttpServletResponse response) throws IOException{
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(GlobalConstant.USER_NAME.equals(cookie.getName())){
               cookie.setMaxAge(0);
               response.addCookie(cookie);
               redisUtil.del(cookie.getValue());
            }
            if (GlobalConstant.USER_TOKEN.equals(cookie.getName())){
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        RequestHolder.remove();
        response.sendRedirect("/water-cup/login");
    }
}
