package com.pzy.study.controller;

import com.pzy.study.base.commons.enums.WebBaseExceptionEnum;
import com.pzy.study.base.commons.exceptions.WebBaseException;
import com.pzy.study.base.commons.utils.RequestHolder;
import com.pzy.study.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-19
 */
@Controller
@RequestMapping("/water-cup")
public class LoginController {

    @RequestMapping(value = "/login" ,method = RequestMethod.POST)
    public String login(@Valid UserEntity userEntity , HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult){
        //登录成功
        request.getSession().setAttribute("user" ,userEntity);
//        RequestHolder.add(userEntity);
//        RequestHolder.add(request);
        return "redirect:/water-cup/main";
    }

    @GetMapping("main")
    public String main(HttpServletRequest request){
        return "admin";
    }

}
