package com.example.abanoub.onlineshopping;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Activity_Laptops extends AppCompatActivity {

    ECommerceDBHelper cart;
    Cursor cursor;

    Products prods;
    ArrayList<Products> arrList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__laptops);

        cart = new ECommerceDBHelper(getApplicationContext());
        cursor = cart.startUp();

        prods = new Products();
        arrList = new ArrayList<Products>();
        arrList = prods.fetchAllProducts();

        Button btn13 = (Button)findViewById(R.id.addCartBtn_13);
        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!cursor.moveToFirst()) || cursor.getString(4).equals("Out"))
                {
                    Toast.makeText(getApplicationContext(), "Please login firstly !", Toast.LENGTH_LONG).show();
                    return;
                }
                if (arrList.get(12).Quantity == 0)
                {
                    Toast.makeText(getApplicationContext(), "Sold Out !", Toast.LENGTH_LONG).show();
                    return;
                }
                cart.addToCart(cursor.getInt(0), arrList.get(12).ProdID, arrList.get(12).ProdName, arrList.get(12).Price, arrList.get(12).Quantity, arrList.get(12).CatID, R.drawable.ic_laptops_1);
                Toast.makeText(getApplicationContext(), "Added !", Toast.LENGTH_LONG).show();
            }
        });

        Button btn14 = (Button)findViewById(R.id.addCartBtn_14);
        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!cursor.moveToFirst()) || cursor.getString(4).equals("Out"))
                {
                    Toast.makeText(getApplicationContext(), "Please login firstly !", Toast.LENGTH_LONG).show();
                    return;
                }
                if (arrList.get(13).Quantity == 0)
                {
                    Toast.makeText(getApplicationContext(), "Sold Out !", Toast.LENGTH_LONG).show();
                    return;
                }
                cart.addToCart(cursor.getInt(0), arrList.get(13).ProdID, arrList.get(13).ProdName, arrList.get(13).Price, arrList.get(13).Quantity, arrList.get(13).CatID, R.drawable.ic_laptops_2);
                Toast.makeText(getApplicationContext(), "Added !", Toast.LENGTH_LONG).show();
            }
        });

        Button btn15 = (Button)findViewById(R.id.addCartBtn_15);
        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!cursor.moveToFirst()) || cursor.getString(4).equals("Out"))
                {
                    Toast.makeText(getApplicationContext(), "Please login firstly !", Toast.LENGTH_LONG).show();
                    return;
                }
                if (arrList.get(14).Quantity == 0)
                {
                    Toast.makeText(getApplicationContext(), "Sold Out !", Toast.LENGTH_LONG).show();
                    return;
                }
                cart.addToCart(cursor.getInt(0), arrList.get(14).ProdID, arrList.get(14).ProdName, arrList.get(14).Price, arrList.get(14).Quantity, arrList.get(14).CatID, R.drawable.ic_laptops_3);
                Toast.makeText(getApplicationContext(), "Added !", Toast.LENGTH_LONG).show();
            }
        });

        Button btn16 = (Button)findViewById(R.id.addCartBtn_16);
        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!cursor.moveToFirst()) || cursor.getString(4).equals("Out"))
                {
                    Toast.makeText(getApplicationContext(), "Please login firstly !", Toast.LENGTH_LONG).show();
                    return;
                }
                if (arrList.get(15).Quantity == 0)
                {
                    Toast.makeText(getApplicationContext(), "Sold Out !", Toast.LENGTH_LONG).show();
                    return;
                }
                cart.addToCart(cursor.getInt(0), arrList.get(15).ProdID, arrList.get(15).ProdName, arrList.get(15).Price, arrList.get(15).Quantity, arrList.get(15).CatID, R.drawable.ic_laptops_4);
                Toast.makeText(getApplicationContext(), "Added !", Toast.LENGTH_LONG).show();
            }
        });

    }
}
