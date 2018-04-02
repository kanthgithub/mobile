package com.reji.mobile.service;

import com.reji.mobile.controller.AccountBalanceQueryController;
import com.reji.mobile.repository.AccountStore;
import com.reji.mobile.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountBalanceQueryServiceProxy extends AccountBalanceQueryServiceImpl {

    private final Logger log = LoggerFactory.getLogger(AccountBalanceQueryServiceProxy.class);

    public AccountBalanceQueryServiceProxy(AccountStore accountStore) {
        super(accountStore);
    }

    private Boolean accountBalanceDelayRequired;
    private Integer accountBalanceDelayPeriodInMilliSec;

    public AccountBalanceQueryServiceProxy setAccountBalanceDelayRequired(Boolean accountBalanceDelayRequired) {
        this.accountBalanceDelayRequired = accountBalanceDelayRequired;
        return this;
    }

    public AccountBalanceQueryServiceProxy setAccountBalanceDelayPeriodInMilliSec(Integer accountBalanceDelayPeriodInMilliSec) {
        this.accountBalanceDelayPeriodInMilliSec = accountBalanceDelayPeriodInMilliSec;
        return this;
    }

    public AccountBalanceResposeModel queryAccount(AccountBalanceRequestModel accountBalanceRequestModel) {
        if(accountBalanceDelayRequired){
            log.info("AccountBalanceQueryServiceProxy: into Limbo State for: {}",accountBalanceDelayPeriodInMilliSec);
            addDelay(accountBalanceDelayPeriodInMilliSec);
            log.info("AccountBalanceQueryServiceProxy: wakeup from Limbo after sleeping for: {}",accountBalanceDelayPeriodInMilliSec);

        }

        return execute(accountBalanceRequestModel);

    }

    public void addDelay(Integer accountBalanceDelayPeriodInMilliSec){

        try{
            Thread.sleep(accountBalanceDelayPeriodInMilliSec);
        }catch(Exception excep){

            log.error("error during adding Delay");

        }


    }

}
