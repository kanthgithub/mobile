package com.reji.mobile.task;

import com.reji.mobile.model.AccountMaintenanceRequestModel;
import com.reji.mobile.model.AccountMaintenanceResposeModel;
import com.reji.mobile.service.IAccountMaintenanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.Callable;

public class AccountCleanupTask implements Callable<ResponseEntity<?>> {

    private final IAccountMaintenanceService accountMaintenanceService;

    public AccountCleanupTask(IAccountMaintenanceService accountMaintenanceService) {


        if (accountMaintenanceService == null) {
            throw new IllegalStateException("accountMaintenanceService is not set.");
        }

        this.accountMaintenanceService = accountMaintenanceService;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public ResponseEntity<?> call() throws Exception {
                accountMaintenanceService.clearAllAccounts();

        return new ResponseEntity<Boolean>(
                Boolean.TRUE, HttpStatus.OK);

    }

}