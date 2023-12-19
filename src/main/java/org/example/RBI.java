package org.example;

import java.io.BufferedReader;
import java.io.IOException;

public interface RBI {
    public void createNewAccount();
    public void setCurrentCustomer(Customer cust);
    public void setCurrentAadhar(String aadhar);
    public void setCurrentAccount(Account acc);
    public boolean isAccountPresent();
    public void getCustomerDetails();
    public void checkBalance();
    public void openFD() throws IOException;
    public void withdraw() throws IOException;
    public void deposit() throws IOException;
    public void applyLoan() throws IOException;
    public void performOperation(Customer cust) throws IOException;
}
