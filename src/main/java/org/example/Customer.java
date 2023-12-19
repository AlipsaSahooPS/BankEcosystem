package org.example;

public class Customer {
    private String custName;
    private String custEmail;
    private String custAddress;
    private String custNumber;
    private String custAadhar;
    private String custGender;
    public Customer(String name, String email, String number, String address, String aadhar , String gender) {
        custName = name;
        custEmail = email;
        custAddress = address;
        custNumber = number;
        custAadhar = aadhar;
        custGender = gender;
    }
    public String getCustName() {
        return custName;
    }
    public String getCustEmail() {
        return custEmail;
    }
    public String getCustAddress() {
        return custAddress;
    }
    public String getCustNumber() {
        return custNumber;
    }
    public String getCustAadhar() {
        return custAadhar;
    }
    public String getCustGender() {
        return custGender;
    }
    public void setCustName(String name) {
        custName = name;
    }
    public void setCustEmail(String email) {
        custEmail = email;
    }
    public void setCustAddress(String address) {
        custAddress = address;
    }
    public void setCustGender(String customerGender) {
        custGender = customerGender;
    }
    public void setCustAadhar(String aadhar) {
        custAadhar = aadhar;
    }
    public void setCustNumber(String number) {
        custNumber = number;
    }
}




