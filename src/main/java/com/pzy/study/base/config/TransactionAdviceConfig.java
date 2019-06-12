package com.pzy.study.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/*
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/06/12
 */
@Configuration
public class TransactionAdviceConfig {

    @Autowired
    private PlatformTransactionManager platformTransactionManager;
}
