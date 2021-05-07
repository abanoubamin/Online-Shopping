package com.example.abanoub.onlineshopping;

public class orderDetails {

    public int OrdID, ProdID, Quantity;

    public orderDetails() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public orderDetails(int ordID, int prodID, int quantity) {
        OrdID = ordID;
        ProdID = prodID;
        Quantity = quantity;
    }

}
