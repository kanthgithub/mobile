package com.reji.mobile.service;

import com.reji.mobile.model.AccountTopupRequestModel;
import com.reji.mobile.model.AccountTopupResposeModel;

public interface IAccountTopupService {

    public AccountTopupResposeModel topupAccount(AccountTopupRequestModel accountTopupRequestModel);

    }
