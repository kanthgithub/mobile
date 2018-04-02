package com.reji.mobile.service;

import com.reji.mobile.model.AccountMaintenanceRequestModel;
import com.reji.mobile.model.AccountMaintenanceResposeModel;

public interface IAccountMaintenanceService {

    public AccountMaintenanceResposeModel addAccount(AccountMaintenanceRequestModel accountMaintenanceRequestModel);

    public void clearAllAccounts();

    }
