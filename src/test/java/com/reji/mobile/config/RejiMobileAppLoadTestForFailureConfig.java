package com.reji.mobile.config;

import com.reji.mobile.configuration.AsyncConfig;
import com.reji.mobile.repository.AccountStore;
import com.reji.mobile.service.AccountBalanceQueryServiceProxy;
import com.reji.mobile.service.AccountTopupServiceProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RejiMobileAppLoadTestForFailureConfig extends AsyncConfig {


    @Value("${accountBalance.loadtest.delayRequired}")
    private Boolean accountBalanceDelayRequired;

    @Value("${accountBalance.loadtest_failure.delayPeriodInMilliSec}")
    private Integer accountBalanceDelayPeriodInMilliSec;


    @Value("${accountTopup.loadtest.delayRequired}")
    private Boolean accountTopupDelayRequired;

    @Value("${accountTopup.loadtest_failure.delayPeriodInMilliSec}")
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
