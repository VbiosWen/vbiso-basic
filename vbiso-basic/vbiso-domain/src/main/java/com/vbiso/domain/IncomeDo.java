package com.vbiso.domain;

public class IncomeDo {

    private long incomeId;

    private long userId;

    private long incomeDate;

    private double incomeData;

    private String incomeDesc;

    public long getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(long incomeId) {
        this.incomeId = incomeId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(long incomeDate) {
        this.incomeDate = incomeDate;
    }

    public double getIncomeData() {
        return incomeData;
    }

    public void setIncomeData(double incomeData) {
        this.incomeData = incomeData;
    }

    public String getIncomeDesc() {
        return incomeDesc;
    }

    public void setIncomeDesc(String incomeDesc) {
        this.incomeDesc = incomeDesc;
    }
}
