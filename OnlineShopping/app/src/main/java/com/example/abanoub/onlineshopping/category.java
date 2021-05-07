package com.example.abanoub.onlineshopping;

public class category {

    public int CatID;
    public String CatName;

    public category() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public category(int catID, String catName) {
        CatID = catID;
        CatName = catName;
    }

}
