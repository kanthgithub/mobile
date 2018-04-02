package com.reji.mobile.controller;

import com.reji.mobile.repository.AccountStore;
import com.reji.mobile.service.IAccountBalanceQueryService;
import com.reji.mobile.task.QueryAccountTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.scheduling.annotation.*;
import org.springframework.web.bind.annotation.*;
import java.math.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.*;

import java.sql.Date;
import java.util.concurrent.*;
import com.reji.mobile.model.*;


@Controller
public class AccountBalanceQueryController {

    private final Logger log = org.slf4j.LoggerFactory.getLogger(AccountBalanceQueryController.class);

    @Autowired
    private IAccountBalanceQueryService accountBalanceQueryService;

    @RequestMapping(value = "/rejiMobile/getAccountBalance/{account}", method = RequestMethod.GET)
    public Callable<ResponseEntity<?>> getAccountBalance(@PathVariable Integer account) {
        log.info("AccountBalanceQueryController queryBalance for account {}",account);

        return new QueryAccountTask(new AccountBalanceRequestModel().setAccount(account), accountBalanceQueryService);
    }

}
