package com.reji.mobile.controller;

import com.reji.mobile.RejiMobileApplication;
import com.reji.mobile.config.RejiMobileAppLoadTestForFailureConfig;
import com.reji.mobile.model.AccountBalanceResposeModel;
import com.reji.mobile.model.AccountMaintenanceResposeModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;


/**
 * Re
 */
@ContextConfiguration(classes = {RejiMobileAppLoadTestForFailureConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {RejiMobileApplication.class},
                webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
public class RejiMobileAppIntegrationTimeoutTest {

    @LocalServerPort
    private int serverport;

    private String url;

    RestTemplate restTemplate = new RestTemplate();


    @Before
    public void setUp() throws Exception{
        url="http://localhost:"+serverport;
    }

    public void initializeAccounts(String account,String initialBalance) throws Exception{
        String addAccountsURL = String.format("%s/rejiMobile/addAccount/%s/%s",url,account,initialBalance);
        restTemplate.postForEntity(addAccountsURL,null,AccountMaintenanceResposeModel.class);

    }

    public void cleanUpAccounts() throws Exception{
        String cleanUpAccountsURL = url+"/rejiMobile/cleanAllAccounts";
        restTemplate.postForEntity(cleanUpAccountsURL,null,Boolean.class);
    }

    @Test
    public void load_Testing_Async_Calls() throws Exception{
        cleanUpAccounts();
        initializeAccounts("99875432","100.0000");
        String queryResourceUrl = url+"/rejiMobile/getAccountBalance/99875432";
        ResponseEntity<AccountBalanceResposeModel> response = restTemplate.getForEntity(queryResourceUrl, AccountBalanceResposeModel.class);
        assertNotNull(response);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.SERVICE_UNAVAILABLE));
    }








}
