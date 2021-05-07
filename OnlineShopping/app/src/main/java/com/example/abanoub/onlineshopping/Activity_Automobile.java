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

public class Activity_Automobile extends AppCompatActivity {

    ECommerceDBHelper cart;
    Cursor cursor;

    Products prods;
    ArrayList<Products> arrList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__automobile);

        cart = new ECommerceDBHelper(getApplicationContext());
        cursor = cart.startUp();

        prods = new Products();
        arrList = new ArrayList<Products>();
        arrList = prods.fetchAllProducts();

        Button btn21 = (Button)findViewById(R.id.addCartBtn_21);
        btn21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!cursor.moveToFirst()) || cursor.getString(4).equals("Out"))
                {
                    Toast.makeText(getApplicationContext(), "Please login firstly !", Toast.LENGTH_LONG).show();
                    return;
                }
                if (arrList.get(20).Quantity == 0)
                {
                    Toast.makeText(getApplicationContext(), "Sold Out !", Toast.LENGTH_LONG).show();
                    return;
                }
                cart.addToCart(cursor.getInt(0), arrList.get(20).ProdID, arrList.get(20).ProdName, arrList.get(20).Price, arrList.get(20).Quantity, arrList.get(20).CatID, R.drawable.ic_automobile_1);
                Toast.makeText(getApplicationContext(), "Added !", Toast.LENGTH_LONG).show();
            }
        });

        Button btn22 = (Button)findViewById(R.id.addCartBtn_22);
        btn22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!cursor.moveToFirst()) || cursor.getString(4).equals("Out"))
                {
                    Toast.makeText(getApplicationContext(), "Please login firstly !", Toast.LENGTH_LONG).show();
                    return;
                }
                if (arrList.get(21).Quantity == 0)
                {
                    Toast.makeText(getApplicationContext(), "Sold Out !", Toast.LENGTH_LONG).show();
                    return;
                }
                cart.addToCart(cursor.getInt(0), arrList.get(21).ProdID, arrList.get(21).ProdName, arrList.get(21).Price, arrList.get(21).Quantity, arrList.get(21).CatID, R.drawable.ic_automobile_2);
                Toast.makeText(getApplicationContext(), "Added !", Toast.LENGTH_LONG).show();
            }
        });

        Button btn23 = (Button)findViewById(R.id.addCartBtn_23);
        btn23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!cursor.moveToFirst()) || cursor.getString(4).equals("Out"))
                {
                    Toast.makeText(getApplicationContext(), "Please login firstly !", Toast.LENGTH_LONG).show();
                    return;
                }
                if (arrList.get(22).Quantity == 0)
                {
                    Toast.makeText(getApplicationContext(), "Sold Out !", Toast.LENGTH_LONG).show();
                    return;
                }
                cart.addToCart(cursor.getInt(0), arrList.get(22).ProdID, arrList.get(22).ProdName, arrList.get(22).Price, arrList.get(22).Quantity, arrList.get(22).CatID, R.drawable.ic_automobile_3);
                Toast.makeText(getApplicationContext(), "Added !", Toast.LENGTH_LONG).show();
            }
        });

        Button btn24 = (Button)findViewById(R.id.addCartBtn_24);
        btn24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!cursor.moveToFirst()) || cursor.getString(4).equals("Out"))
                {
                    Toast.makeText(getApplicationContext(), "Please login firstly !", Toast.LENGTH_LONG).show();
                    return;
                }
                if (arrList.get(23).Quantity == 0)
                {
                    Toast.makeText(getApplicationContext(), "Sold Out !", Toast.LENGTH_LONG).show();
                    return;
                }
                cart.addToCart(cursor.getInt(0), arrList.get(23).ProdID, arrList.get(23).ProdName, arrList.get(23).Price, arrList.get(23).Quantity, arrList.get(23).CatID, R.drawable.ic_automobile_4);
                Toast.makeText(getApplicationContext(), "Added !", Toast.LENGTH_LONG).show();
            }
        });


    }
}
