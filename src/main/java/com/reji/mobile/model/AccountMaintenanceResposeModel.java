package com.reji.mobile.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class AccountMaintenanceResposeModel implements Serializable
{
    private Integer account;
    private BigDecimal accountBalance;
    private String messageDescription;
    private Boolean isSuccessful;

    public Integer getAccount() {
        return account;
    }

    public AccountMaintenanceResposeModel setAccount(Integer account) {
        this.account = account;
        return this;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public AccountMaintenanceResposeModel setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
        return this;
    }

    public String getMessageDescription() {
        return messageDescription;
    }

    public AccountMaintenanceResposeModel setMessageDescription(String messageDescription) {
        this.messageDescription = messageDescription;
        return this;
    }

    public Boolean getSuccessful() {
        return isSuccessful;
    }

    public AccountMaintenanceResposeModel setSuccessful(Boolean successful) {
        isSuccessful = successful;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountMaintenanceResposeModel that = (AccountMaintenanceResposeModel) o;
        return Objects.equals(account, that.account) &&
                Objects.equals(accountBalance, that.accountBalance) &&
                Objects.equals(messageDescription, that.messageDescription) &&
                Objects.equals(isSuccessful, that.isSuccessful);
    }

    @Override
    public int hashCode() {

        return Objects.hash(account, accountBalance, messageDescription, isSuccessful);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AccountTopupResposeModel{");
        sb.append("account=").append(account);
        sb.append(", accountBalance=").append(accountBalance);
        sb.append(", messageDescription='").append(messageDescription).append('\'');
        sb.append(", isSuccessful=").append(isSuccessful);
        sb.append('}');
        return sb.toString();
    }
}
