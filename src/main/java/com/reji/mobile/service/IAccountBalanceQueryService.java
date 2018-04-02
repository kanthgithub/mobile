package com.reji.mobile.service;

import com.reji.mobile.model.AccountBalanceRequestModel;
import com.reji.mobile.model.AccountBalanceResposeModel;


public interface IAccountBalanceQueryService {

    public AccountBalanceResposeModel queryAccount(AccountBalanceRequestModel AccountBalanceRequestModel);
}
