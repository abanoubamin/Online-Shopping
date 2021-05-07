package com.example.abanoub.onlineshopping;

public class Orders {

    public int OrdID, CustID;
    public String OrdDate, Address;

    public Orders() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Orders(int ordID, String ordDate, int custID, String address) {
        OrdID = ordID;
        OrdDate = ordDate;
        CustID = custID;
        Address = address;
    }

}
