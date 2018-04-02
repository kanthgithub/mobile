package com.reji.mobile.service;

import com.reji.mobile.exception.InValidAccountException;
import com.reji.mobile.exception.InvalidTopupAmountException;
import com.reji.mobile.model.*;
import com.reji.mobile.repository.AccountStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.reji.mobile.util.MathHelper.isValidAmount;

@Component
public class AccountBalanceQueryServiceImpl implements IAccountBalanceQueryService {

    @Autowired
    private AccountStore accountStore;

    public AccountBalanceResposeModel queryAccount(AccountBalanceRequestModel accountBalanceRequestModel){

        BigDecimal accountBalance = null;

        Boolean isSuccessful = Boolean.FALSE;

        String messageDescription = null;

        try {
            accountBalance = accountStore.queryBalance(accountBalanceRequestModel.getAccount());
            isSuccessful = Boolean.TRUE;
            messageDescription =
                    String.format("Account: %d has Balance: %s"
                            , accountBalanceRequestModel.getAccount(), String.valueOf(accountBalance));
        } catch (InValidAccountException excep) {
            messageDescription = excep.getDescription();
        }

        return new AccountBalanceResposeModel().setAccount(accountBalanceRequestModel.getAccount()).setBalance(accountBalance)
                .setSuccessful(isSuccessful).setMessageDescription(messageDescription);


    }

}
