package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import  java.util.logging.Level;
import java.util.logging.Logger;
import  java.util.logging.*;

import static org.example.Main.LOGGER;

public class SBI implements RBI {
    InputStreamReader isr;
    BufferedReader buff;
    private float ROI;
    private float minBalance;
    private float withdrawTransactionCharge;
    Customer curCust;
    Account curAcc;
    String curAadhar;
    float[] loanBankPercent;
    private float creditCardBankPercent;
    static HDFC hdfcObj = null;
    static Map<String, Account> customerAccountRecord = null;

    public SBI(InputStreamReader isr, BufferedReader buff) {
        this.isr = isr;
        this.buff = buff;
        if(customerAccountRecord == null)
            customerAccountRecord = new HashMap<>();
        ROI = 8;
        minBalance = 1000;
        loanBankPercent = new float[]{5.0f, 3.0f, 6.0f, 8.0f};
        withdrawTransactionCharge = 0.02f;
        creditCardBankPercent = 2;
    }

    public static HDFC getBank(InputStreamReader isr, BufferedReader buff) {
        if (hdfcObj == null) {
            hdfcObj = new HDFC(isr, buff);
        }
        return hdfcObj;
    }

    public void createNewAccount() {
        setCurrentAccount(new Account());
        customerAccountRecord.put(curAadhar, curAcc);
    }

    public void setCurrentCustomer(Customer cust) {
        curCust = cust;
    }

    public void setCurrentAadhar(String aadhar) {
        curAadhar = aadhar;
    }

    public void setCurrentAccount(Account acc) {
        curAcc = acc;
    }

    public boolean isAccountPresent() {
        return customerAccountRecord.containsKey(curAadhar);
    }

    public void getCustomerDetails() {
        LOGGER.log(Level.INFO,"Customer name "+ curCust.getCustName());
        LOGGER.log(Level.INFO,"Customer email "+ curCust.getCustEmail());
        LOGGER.log(Level.INFO,"Customer phone  "+ curCust.getCustNumber());
        LOGGER.log(Level.INFO,"Customer aadhar "+ curCust.getCustAadhar());
        LOGGER.log(Level.INFO,"Customer address"+ curCust.getCustAddress());
        LOGGER.log(Level.INFO,"Customer gender"+ curCust.getCustGender());
    }

    public void checkBalance() {
        LOGGER.log(Level.INFO,"Your savings account balance is " + curAcc.getBalance() + ".");
    }

    public void openFD() throws IOException {
        LOGGER.log(Level.INFO,"Enter the amount you want to open for FD:");
        float amount = Float.parseFloat(buff.readLine());
        LOGGER.log(Level.INFO,"Enter for how many years you want have the FD:");
        float years = Integer.parseInt(buff.readLine());
        float interest = (float)(amount * Math.pow((1 + (ROI / 100.0)), years));
        float requiredAmount = interest - amount;
        LOGGER.log(Level.INFO,"The interest accumulated over years is " + interest + ".");
    }

    public void withdraw() throws IOException {
        LOGGER.log(Level.INFO,"Enter the amount you want to withdraw");
        float amount = Float.parseFloat(buff.readLine());
        float withdrawAmount = amount;
        float transactionCharge = curAcc.withdrawCount > 2 ? withdrawTransactionCharge : 0f;
        withdrawAmount += amount * transactionCharge;
        if (curAcc.balance - withdrawAmount > minBalance) {
            curAcc.setBalance(curAcc.balance - withdrawAmount);
            curAcc.setwithdrawCount(curAcc.withdrawCount+1);
            LOGGER.log(Level.INFO,"Amount of " + amount + " withdrawn." + (withdrawAmount - amount) + " is charged extra. Current balance is " + curAcc.balance);
        } else {
            LOGGER.log(Level.INFO,"Cannot withdraw. Not enough balance.");
        }
    }

    public void deposit() throws IOException {
        LOGGER.log(Level.INFO,"Enter the amount you want to deposit:");
        float amount = Float.parseFloat(buff.readLine());
        if(curAcc.balance==0.0) {
            if(amount>minBalance) {
                curAcc.setBalance(curAcc.balance+amount);
                LOGGER.log(Level.INFO,"Amount has been deposited in your account");
            }
            else {
                LOGGER.log(Level.INFO,"Amount is less than min balance of " + minBalance + ".");
            }
        }
        else {
            curAcc.setBalance(curAcc.balance+amount);
            LOGGER.log(Level.INFO,"Amount has been deposited in your account");
        }
    }

    public void applyLoan() throws IOException {
        float rate;
        float amount, years;
        LOGGER.log(Level.INFO,"Enter the amount you want loan for:");
        amount = Float.parseFloat(buff.readLine());
        LOGGER.log(Level.INFO,"Enter for how many years you want:");
        years = Integer.parseInt(buff.readLine());
        LOGGER.log(Level.INFO,"Select the loan type:\n1.Home.\n2.Education.\n3.Personal.\n4.Car.");
        int loanType = Integer.parseInt(buff.readLine());
        switch(loanType) {
            case 1:
                rate = loanBankPercent[0];
                break;
            case 2:
                rate = loanBankPercent[1];
                break;
            case 3:
                rate = loanBankPercent[2];
                break;
            case 4:
                rate = loanBankPercent[3];
                break;
            default:
                rate = ROI;
                break;
        }
        float prinDeduction = amount / years;
        LOGGER.log(Level.INFO,"Interest per year will be as following");
        for (int i = 0; i < years; i++) {
            System.out.println(amount * rate * 0.01);
            amount -= prinDeduction;
        }
    }

    public void performOperation(Customer cust) throws IOException {
        setCurrentCustomer(cust);
        setCurrentAadhar(curCust.getCustAadhar());
        boolean isAccPresent = isAccountPresent(), banking = true;
        int bankingChoice = 0;
        if(isAccPresent) {
            setCurrentAccount(customerAccountRecord.get(curAadhar));
        }
        else {
            boolean accountingPreference = false;
            String preferenceChoice = "";
            while(!accountingPreference)
            {
                LOGGER.log(Level.INFO,"Please select your accounting preference:\n1.Press 1 to create an account with HDFC.\n2.Press N to exit IBS.");
                preferenceChoice= buff.readLine();
                switch(preferenceChoice)
                {
                    case "1":
                        createNewAccount();
                        accountingPreference = true;
                        banking = true;
                        break;
                    case "N":
                        accountingPreference = true;
                        banking = false;
                        LOGGER.log(Level.INFO,"Thank you for using IBS.");
                        break;
                    default:
                        accountingPreference = false;
                        banking = true;
                        LOGGER.log(Level.INFO,"Please select valid option.");
                        break;
                }
            }
        }
        while(banking) {
            LOGGER.log(Level.INFO,"Enter the operation you want to perform:\n1.Deposit.\n2.Withdraw.\n3.Open a new FD.\n4.Check your savings account balance.\n5.Get all customer details.\n6.Get Loan Details.\n7.Exit");
            bankingChoice = Integer.parseInt(buff.readLine());
            switch (bankingChoice) {
                case 1:
                    deposit();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    openFD();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    getCustomerDetails();
                    break;
                case 6:
                    applyLoan();
                    break;
                case 7:
                    LOGGER.log(Level.INFO,"Thank you for using IBS.");
                    banking = false;
                    break;
                default:
                    LOGGER.log(Level.INFO,"Please select valid option.");
            }
        }
    }
}
