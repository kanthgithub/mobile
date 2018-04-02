package com.reji.mobile.task;

import com.reji.mobile.exception.InValidAccountException;
import com.reji.mobile.model.AccountBalanceRequestModel;
import com.reji.mobile.model.AccountBalanceResposeModel;
import com.reji.mobile.repository.AccountStore;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

import com.reji.mobile.service.IAccountBalanceQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.reji.mobile.exception.*;

public class QueryAccountTask implements Callable<ResponseEntity<?>> {


    private final AccountBalanceRequestModel accountBalanceRequestModel;

    private IAccountBalanceQueryService accountBalanceQueryService;

    public QueryAccountTask(AccountBalanceRequestModel accountBalanceRequestModel, IAccountBalanceQueryService accountBalanceQueryService) {

        if (accountBalanceRequestModel == null) {
            throw new IllegalStateException("account is not set.");
        }

        if (accountBalanceQueryService == null) {
            throw new IllegalStateException("accountBalanceQueryService is not set.");
        }

        this.accountBalanceRequestModel = accountBalanceRequestModel;
        this.accountBalanceQueryService = accountBalanceQueryService;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public ResponseEntity<?> call() throws Exception {

        AccountBalanceResposeModel accountBalanceResposeModel = accountBalanceQueryService.queryAccount(accountBalanceRequestModel);
        return new ResponseEntity<AccountBalanceResposeModel>(
                accountBalanceResposeModel, HttpStatus.OK);
    }

}