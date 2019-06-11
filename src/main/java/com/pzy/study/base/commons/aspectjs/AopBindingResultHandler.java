package com.pzy.study.base.commons.aspectjs;

import com.pzy.study.base.commons.enums.WebBaseExceptionEnum;
import com.pzy.study.base.commons.exceptions.WebBaseException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import javax.validation.ValidationException;


/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-04
 */
@Component
@Aspect
public class AopBindingResultHandler {

    @Pointcut("execution(* com.pzy.study.controller.*.*(..))&& args(..,bindingResult)")
    public void pointCut(BindingResult bindingResult){}

    @Before("pointCut(bindingResult)")
    public void beforeController(JoinPoint joinPoint , BindingResult bindingResult) {
            if (bindingResult.hasErrors()){
                throw new ValidationException(bindingResult.getAllErrors().get(0).getDefaultMessage());
            }
    }
}
