//package org.example;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//
//public class IDFC  {
//
//    InputStreamReader isr;
//    BufferedReader buff;
//    float balance = 0.0f;
//    float amount;
//    float remainingBalance;
//    float ROI = 8.5f;
//    int years;
//    int withdrawCount =0;
//    float minBalance = 1000;
//    ArrayList<Customer> customersList;
//
//    public IDFC(Main isr , String buff) {
//
//        this.isr = isr;
//        this.buff = buff;
//        this.customersList = new ArrayList<>();
//    }
//
//    public void performOperation() throws IOException {
//
//
//
//        boolean flag = true;
//
//        while(flag) {
//            System.out.println("Enter the operation you want to perform \n1.Deposit \n2.Withdraw \n3.openFD \n4.checkBalance \n5.createAccount \n6.Get all customer details \n7.Exit");
//            int choice = 0;
//            choice = Integer.parseInt(buff.readLine());
//            switch (choice) {
//                case 1:
//                    System.out.println("Enter the amount you want to deposit");
//                    amount = Float.parseFloat(buff.readLine());
//                    remainingBalance = deposit(amount);
//                    System.out.println("Amount has been deposited in your account" + remainingBalance);
//                    break;
//
//                case 2:
//                    System.out.println("Enter the amount you want to withdraw");
//                    amount = Float.parseFloat(buff.readLine());
//                    remainingBalance = withdraw(amount);
//                    System.out.println("Amount has been withdrawn from your account" + remainingBalance);
//                    break;
//
//                case 3:
//                    System.out.println("Enter the amount you want to open for FD");
//                    amount = Float.parseFloat(buff.readLine());
//                    System.out.println("Enter  for how many years you want fixed deposit ");
//                    years = Integer.parseInt(buff.readLine());
//                    remainingBalance = openFD(amount, years);
//                    System.out.println("The interest accumulated over years is " + remainingBalance);
//                    break;
//
//                case 4:
//                    remainingBalance = checkBalance();
//                    System.out.println("Your balance is" + remainingBalance);
//
//
//                case 5:
//                    System.out.println("Enter your details to open an account ");
//                    System.out.println("Enter your name");
//                    String name = buff.readLine();
//                    System.out.println("enter your email");
//                    String email = buff.readLine();
//                    System.out.println("Enter your phone");
//                    String number = buff.readLine();
//                    System.out.println("Enter your address");
//                    String address = buff.readLine();
//                    System.out.println("Enter your Aadhar");
//                    String aadhar = buff.readLine();
//                    System.out.println("Enter your Gender");
//                    String gender = buff.readLine();
//                    Customer customer = new Customer(name, email, number, address, aadhar, gender);
//
//                    customersList.add(customer);
//
//
//                    break;
//
//                case 6:
//                    for(Customer cust: customersList)
//                        getCustomerDetails(cust);
//                    break;
//                case 7:
//                    System.out.println("Exiting...");
//                    flag = false;
//
//                    break;
//
//                default:
//                    System.out.println("Invalid Choice");
//            }
//
//        }
//
//
//    }
//
//    private void getCustomerDetails(Customer customer) {
//        System.out.println("Customer name "+ customer.getCustName());
//        System.out.println("Customer email "+ customer.getCustEmail());
//        System.out.println("Customer phone  "+ customer.getCustNumber());
//        System.out.println("Customer aadhar "+ customer.getCustAadhar());
//        System.out.println("Customer address"+ customer.getCustAddress());
//        System.out.println("Customer gender"+ customer.getCustGender());
//        System.out.println();
//
//    }
//
//
//    private float checkBalance() {
//        return  balance;
//    }
//
//    public float openFD(float amount, int years) {
//        float interest = (float) (amount * Math.pow((1 + (ROI / 100.0)), years));
//        float requiredAmount = interest-amount;
//        return requiredAmount;
//    }
//
//    public float withdraw(float amount) {
//        float withdrawAmount = amount;
//        float transactionCharge = withdrawCount > 2 ? 0.01f: 0f;
//        withdrawAmount += amount * transactionCharge;
//
//        if (balance - withdrawAmount > 1000) {
//            balance -= withdrawAmount;
//            withdrawCount++;
//            System.out.println("Amount of " + amount + " withdrawn." + (withdrawAmount - amount) + " is charged extra. Current balance is " + balance);
//        } else {
//            System.out.println("Cannot withdraw. Not enough balance.");
//        }
//        return balance;
//    }
//
//    public float deposit(float amount) {
//        if(balance==0.0){
//            if(amount>minBalance)
//            {
//                balance += amount;
//            }else
//            {
//                System.out.println("Amount is less than min balance of " + minBalance);
//            }
//
//        } else
//        {
//            balance+=amount;
//        }
//
//        return balance;
//
//    }
//
//
//
//}
