package com.reji.mobile.repository;

import com.reji.mobile.exception.InValidAccountException;
import com.reji.mobile.exception.InvalidTopupAmountException;
import com.reji.mobile.exception.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import static com.reji.mobile.util.MathHelper.isValidAmount;

@Component
public class AccountStore {
    public final Logger log = org.slf4j.LoggerFactory.getLogger(AccountStore.class);

    private Map<Number,BigDecimal> accountStore = new ConcurrentHashMap<>();

    public AccountStore() {

    }

    public void clearAll(){
         accountStore.clear();
    }

    public Boolean isValidAccount(Integer account){
        return accountStore.containsKey(account);
    }

    public BigDecimal addAccount(Integer account,BigDecimal initial_balance){

        if(accountStore.containsKey(account)){
            throw new DuplicateAccountException(String.format("Account %d exists.",account));
        }else{
            accountStore.put(account,initial_balance);
        }

        return accountStore.get(account);
    }

    public BigDecimal queryBalance(Integer account) throws InValidAccountException {

        log.info("accountStore queryBalance for account {}",account);

        if(!isValidAccount(account)){
            String errorDescription = String.format("Account Query Failed - Invalid Account %d",account);
            throw new InValidAccountException(errorDescription);
        }

        return accountStore.get(account);
    }

    public BigDecimal topupAccount(Integer account,BigDecimal topupAmount) throws InValidAccountException {

        log.info("accountStore topupAccount for account: {} and topupAmount: {}",account,topupAmount);

        if(!isValidAccount(account)){
            String errorDescription = String.format("Account topup Failed - Invalid Account %d",account);
            throw new InValidAccountException(errorDescription);
        }


        if(!isValidAmount(topupAmount)){
            String errorDescription = String.format("Account topup Failed - Invalid topupAmount %s",String.valueOf(topupAmount));
            throw new InvalidTopupAmountException(errorDescription);
        }

        BigDecimal accountBalance = accountStore.get(account);

        accountStore.put(account,accountBalance.add(topupAmount));

        return accountStore.get(account);
    }


}
