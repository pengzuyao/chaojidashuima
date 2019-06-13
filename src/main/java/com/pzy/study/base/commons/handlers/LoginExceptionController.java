package com.pzy.study.base.commons.handlers;

import com.pzy.study.base.commons.exceptions.LoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/06/13
 */
@ControllerAdvice
@Controller
@Slf4j
public class LoginExceptionController {

    @ExceptionHandler(LoginException.class)
    public String loginHandler(LoginException e){
            log.info("登录失败日志原因：{}", e.getLoginExceptionEnum().getDesc());
            return "redirect:/water-cup/login";
    }
}
