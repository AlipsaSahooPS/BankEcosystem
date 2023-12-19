package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import  java.util.logging.Level;
import java.util.logging.Logger;
import  java.util.logging.*;

public class Main {
    InputStreamReader isr;
    BufferedReader buff;
    public static HashMap<String,Customer> customers;
    final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public Main(){
        customers = new HashMap<>();
        isr = new InputStreamReader(System.in);
        buff = new BufferedReader(isr);
    }
    public static void main(String[] args) throws IOException {
        Main obj = new Main();
        boolean bankExit = false, bankingPreference = false;
        String preferenceChoice = "", AadharNumber = "", bankChoice="";
        RBI bank = null;
        LOGGER.log(Level.INFO,"Welcome to IBS");
        while(!bankingPreference)
        {
            LOGGER.log(Level.INFO, "Please Select your banking preference:\n1.Press 1 to enter your Aadhar Number.\n2.Press N to exit IBS.");
            preferenceChoice= obj.buff.readLine();
            switch(preferenceChoice)
            {
                case "1":
                    LOGGER.log(Level.INFO, "Please enter your aadhar number:");
                    AadharNumber = obj.buff.readLine();
                    if (!customers.containsKey(AadharNumber )) {
                        LOGGER.log(Level.INFO,"This aadhar is not registered with the IBS.");
                        LOGGER.log(Level.INFO, "Do you want to register for it?\n1. Press 1 for Yes.\n2.Press 2 for No.");
                        int opt = Integer.parseInt(obj.buff.readLine());
                        if (opt == 1) {
                            Customer cust = obj.createNewCustomer(obj.buff, AadharNumber);
                            customers.put(AadharNumber, cust);
                            bankingPreference = true;
                            bankExit = false;
                        }
                        else
                        {
                            bankingPreference = false;
                            bankExit = false;
                        }
                    }
                    break;
                case "N":
                    bankingPreference = true;
                    bankExit = true;
                    LOGGER.log(Level.INFO,"Thank you for using IBS.");
                    break;
                default:
                    bankingPreference = false;
                    bankExit = false;
                    LOGGER.log(Level.INFO,"Please select valid preference.");
                    break;
            }
        }
        while(!bankExit) {
            LOGGER.log(Level.INFO,"Please select the choice of your bank \n1.Press 1 for HDFC \n2.Press 2 for ICICI \n3.Press 3 for SBI \n4.Press 4 for AXIS\n5.Press 5 for IDFC \n6.Press N to exit IBS");
            bankChoice = obj.buff.readLine();
            Customer cust = customers.get(AadharNumber);
            switch(bankChoice) {
                case "1":
                    LOGGER.log(Level.INFO,"Welcome to HDFC.");
                    bank = HDFC.getBank(obj.isr, obj.buff);
                    bank.performOperation(cust);
                    break;
//                case "2":
//                    System.out.println("Welcome to ICICI.");
//                    bank = ICICI.getBank(obj.isr, obj.buff);
//                    bank.performOperation(cust);
//                    break;
//                case "3":
//                    System.out.println("Welcome to SBI.");
//                    bank = SBI.getBank(obj.isr, obj.buff);
//                    bank.performOperation(cust);
//                    break;
//                case "4":
//                    System.out.println("Welcome to AXIS.");
//                    bank = AXIS.getBank(obj.isr, obj.buff);
//                    bank.performOperation(cust);
//                    break;
//                case "5":
//                    System.out.println("Welcome to IDFC.");
//                    bank = IDFC.getBank(obj.isr, obj.buff);
//                    bank.performOperation(cust);
//                    break;
                case "N":
                    bankExit = true;
                    LOGGER.log(Level.INFO,"Thank you for using IBS.");
                    break;
                default:
                    LOGGER.log(Level.INFO,"Please select valid bank.");
                    break;
            }
            if(!bankExit) {
                LOGGER.log(Level.INFO,"Do you want to continue?\n1.Press 1 to continue \n2.Press N to exit IBS.");
                String continueChoice = obj.buff.readLine();
                switch (continueChoice) {
                    case "1":
                        bankExit = false;
                        break;
                    case "N":
                        bankExit = true;
                        LOGGER.log(Level.INFO,"Thank you for using IBS.");
                        break;
                    default:
                        bankExit = false;
                        LOGGER.log(Level.INFO,"Not a valid option. Opening the banking page.");
                        break;
                }
            }
        }
    }
    public Customer createNewCustomer(BufferedReader buff, String customerAadhar) throws IOException {
        String customerName, customerEmail, customerAddress, customerGender, customerNumber;
        LOGGER.log(Level.INFO,"Enter full name.");
        customerName = buff.readLine();
        LOGGER.log(Level.INFO,"Enter email.");
        customerEmail = buff.readLine();
        LOGGER.log(Level.INFO,"Enter Address.");
        customerAddress = buff.readLine();
        LOGGER.log(Level.INFO,"Enter Gender");
        customerGender = buff.readLine();
        LOGGER.log(Level.INFO,"Enter Phone.");
        customerNumber = buff.readLine();
        return new Customer(customerName, customerEmail, customerNumber, customerAddress, customerAadhar, customerGender);
    }
}