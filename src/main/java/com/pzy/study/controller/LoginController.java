package com.pzy.study.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.cache.GuavaCache;
import com.pzy.study.base.commons.enums.LoginExceptionEnum;
import com.pzy.study.base.commons.exceptions.LoginException;
import com.pzy.study.base.commons.utils.AESUtils;
import com.pzy.study.base.commons.utils.PasswordHelper;
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

    @RequestMapping(value = "/sys/login")
    public void login(@Valid UserEntity userEntity , HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult){
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
        if (rolesByUserId.isEmpty()){
            throw new LoginException(LoginExceptionEnum.request_noRoles);
        }
        List<Integer> collect = rolesByUserId.stream().map(roleEntity -> roleEntity.getId()).distinct().collect(Collectors.toList());
        List<AclEntity> aclsByRoleIds = aclService.findAclsByRoleIds(collect);
        if (aclsByRoleIds.isEmpty()){
            throw new LoginException(LoginExceptionEnum.request_noAcls);
        }
        //登录成功
        request.getSession().setAttribute("user" ,userEntity);
        request.getSession().setMaxInactiveInterval(1800);
        long time = Calendar.getInstance().getTimeInMillis();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("timestamp" ,time);
        jsonObject.put(userEntity.getUsername() , userEntity);
        String content = jsonObject.toJSONString();
        String token = AESUtils.encrypt(key, content);
        log.info("{}" ,token);
        Cookie cookieName = new Cookie("userName" ,userEntity.getUsername());
        Cookie cookieToken = new Cookie(userEntity.getUsername() ,token);
        response.addCookie(cookieName);
        response.addCookie(cookieToken);
        try {
            response.sendRedirect("/water-cup/main");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return "redirect:/water-cup/main";
    }

    @RequestMapping(value = "/main" ,method = RequestMethod.GET)
    public String main(){
        return "admin";
    }

    @RequestMapping(value = "/login" ,method = RequestMethod.GET)
    public String login(){
        return "login";
    }
    
}
