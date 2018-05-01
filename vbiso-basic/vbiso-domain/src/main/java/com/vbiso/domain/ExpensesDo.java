package com.vbiso.domain;

public class ExpensesDo {

    private long expensesId;

    private long userId;

    private long expensesDate;

    private double expensesData;

    private String expensesDesc;

    private long categoryId;

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getExpensesId() {
        return expensesId;
    }

    public void setExpensesId(long expensesId) {
        this.expensesId = expensesId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getExpensesDate() {
        return expensesDate;
    }

    public void setExpensesDate(long expensesDate) {
        this.expensesDate = expensesDate;
    }

    public double getExpensesData() {
        return expensesData;
    }

    public void setExpensesData(double expensesData) {
        this.expensesData = expensesData;
    }

    public String getExpensesDesc() {
        return expensesDesc;
    }

    public void setExpensesDesc(String expensesDesc) {
        this.expensesDesc = expensesDesc;
    }
}
