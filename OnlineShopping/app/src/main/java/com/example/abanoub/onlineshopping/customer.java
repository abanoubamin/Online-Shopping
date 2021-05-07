package com.example.abanoub.onlineshopping;

public class customer {

    public int CustID;
    public String CustName, Username, Password, Gender, Birthday, Job;

    public customer() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public customer(int custID, String custName, String username, String password, String gender, String birthday, String job) {
        CustID = custID;
        CustName = custName;
        Username = username;
        Password = password;
        Gender = gender;
        Birthday = birthday;
        Job = job;
    }

}
