package com.reji.mobile.config;

import com.reji.mobile.configuration.AsyncConfig;
import com.reji.mobile.repository.AccountStore;
import com.reji.mobile.service.AccountBalanceQueryServiceProxy;
import com.reji.mobile.service.AccountTopupServiceProxy;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;

@Configuration
public class RejiMobileAppTestConfig extends AsyncConfig {


    @Value("${accountBalance.delayRequired}")
    private Boolean accountBalanceDelayRequired;

    @Value("${accountBalance.delayPeriodInMilliSec}")
    private Integer accountBalanceDelayPeriodInMilliSec;


    @Value("${accountTopup.delayRequired}")
    private Boolean accountTopupDelayRequired;

    @Value("${accountTopup.delayPeriodInMilliSec}")
    private Integer accountTopupDelayPeriodInMilliSec;


    @Bean
    @Primary
    AccountBalanceQueryServiceProxy accountBalanceQueryServiceProxy(AccountStore accountStore){

        return new AccountBalanceQueryServiceProxy(accountStore)
                .setAccountBalanceDelayRequired(accountBalanceDelayRequired)
                .setAccountBalanceDelayPeriodInMilliSec(accountBalanceDelayPeriodInMilliSec);
    }

    @Bean
    @Primary
    AccountTopupServiceProxy accountTopupServiceProxy(AccountStore accountStore){

        return new AccountTopupServiceProxy(accountStore)
                .setAccountTopupDelayRequired(accountTopupDelayRequired)
                .setAccountTopupDelayPeriodInMilliSec(accountTopupDelayPeriodInMilliSec);
    }



}
