package org.example;

public class Account {
    float balance;
    float FDAmount;
    float loanAmount;
    float withdrawCount;
    float creditCardMaxLimit;
    public Account(){
        balance = 0;
        FDAmount = 0;
        loanAmount = 0;
        creditCardMaxLimit = 0;
        withdrawCount = 0;
    }
    public float getBalance() {
        return balance;
    }
    public void setBalance(float balance) {
        this.balance = balance;
    }
    public float getLoanAmount() {
        return loanAmount;
    }
    public void setLoanAmount(float loanAmount) {
        this.loanAmount = loanAmount;
    }
    public float getFDAmount() {
        return FDAmount;
    }
    public void setFDAmount(float FDAmount) {
        this.FDAmount = FDAmount;
    }
    public float getCreditCardMaxLimit() {
        return creditCardMaxLimit;
    }
    public void setCreditCardMaxLimit(float creditCardMaxLimit) {
        this.creditCardMaxLimit = creditCardMaxLimit;
    }
    public float getwithdrawCount() {
        return withdrawCount;
    }
    public void setwithdrawCount(float withdrawCount) {
        this.withdrawCount = withdrawCount;
    }
}