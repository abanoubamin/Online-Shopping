package com.example.abanoub.onlineshopping;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Products {

    public int ProdID, Price, Quantity, CatID, ImgID;
    public String ProdName;

    public Products() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Products(int prodID, String prodName, int price, int quantity, int catID, int imgID) {
        ProdID = prodID;
        ProdName = prodName;
        Price = price;
        Quantity = quantity;
        CatID = catID;
        ImgID = imgID;
    }

    public ArrayList<Products> fetchAllProducts() {
        FirebaseDatabase fbDb = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = fbDb.getReference("OnShopDb");
        final Products[] prods = {new Products()};
        final ArrayList<Products> arrList = new ArrayList<Products>();
        dbRef.child("Products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dSnap : dataSnapshot.getChildren())
                {
                    prods[0] = dSnap.getValue(Products.class);
                    arrList.add(prods[0]);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return arrList;
    }

}
