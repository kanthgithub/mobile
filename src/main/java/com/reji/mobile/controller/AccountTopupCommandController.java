package com.reji.mobile.controller;

import com.reji.mobile.model.AccountTopupRequestModel;
import com.reji.mobile.repository.AccountStore;
import com.reji.mobile.task.QueryAccountTask;
import com.reji.mobile.task.TopupAccountTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.math.*;
import java.util.concurrent.Callable;
import com.reji.mobile.service.*;

@Controller
public class AccountTopupCommandController {

    private final Logger log = LoggerFactory.getLogger(AccountTopupCommandController.class);

    @Autowired
    private IAccountTopupService  accountTopupService;

    @RequestMapping(value = "/rejiMobile/topupAccount/{account}/{topupAmount:.+}", method = RequestMethod.POST)
    public Callable<ResponseEntity<?>> topupAccount(@PathVariable Integer account,
                                                    @PathVariable String topupAmount) {
        log.info("AccountTopupCommandController queryBalance for account: {} and topupAmount: {}",account,topupAmount);

        return new TopupAccountTask(new AccountTopupRequestModel().setAccount(account)
                                                                    .setTopupAmount(topupAmount),
                                            accountTopupService);
    }

}
