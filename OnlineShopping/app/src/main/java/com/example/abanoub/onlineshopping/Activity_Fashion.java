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

public class Activity_Fashion extends AppCompatActivity {

    ECommerceDBHelper cart;
    Cursor cursor;

    Products prods;
    ArrayList<Products> arrList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__fashion);

        cart = new ECommerceDBHelper(getApplicationContext());
        cursor = cart.startUp();

        prods = new Products();
        arrList = new ArrayList<Products>();
        arrList = prods.fetchAllProducts();

        Button btn5 = (Button)findViewById(R.id.addCartBtn_5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!cursor.moveToFirst()) || cursor.getString(4).equals("Out"))
                {
                    Toast.makeText(getApplicationContext(), "Please login firstly !", Toast.LENGTH_LONG).show();
                    return;
                }
                if (arrList.get(4).Quantity == 0)
                {
                    Toast.makeText(getApplicationContext(), "Sold Out !", Toast.LENGTH_LONG).show();
                    return;
                }
                cart.addToCart(cursor.getInt(0), arrList.get(4).ProdID, arrList.get(4).ProdName, arrList.get(4).Price, arrList.get(4).Quantity, arrList.get(4).CatID, R.drawable.ic_fashion_1);
                Toast.makeText(getApplicationContext(), "Added !", Toast.LENGTH_LONG).show();
            }
        });

        Button btn6 = (Button)findViewById(R.id.addCartBtn_6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!cursor.moveToFirst()) || cursor.getString(4).equals("Out"))
                {
                    Toast.makeText(getApplicationContext(), "Please login firstly !", Toast.LENGTH_LONG).show();
                    return;
                }
                if (arrList.get(5).Quantity == 0)
                {
                    Toast.makeText(getApplicationContext(), "Sold Out !", Toast.LENGTH_LONG).show();
                    return;
                }
                cart.addToCart(cursor.getInt(0), arrList.get(5).ProdID, arrList.get(5).ProdName, arrList.get(5).Price, arrList.get(5).Quantity, arrList.get(5).CatID, R.drawable.ic_fashion_2);
                Toast.makeText(getApplicationContext(), "Added !", Toast.LENGTH_LONG).show();
            }
        });

        Button btn7 = (Button)findViewById(R.id.addCartBtn_7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!cursor.moveToFirst()) || cursor.getString(4).equals("Out"))
                {
                    Toast.makeText(getApplicationContext(), "Please login firstly !", Toast.LENGTH_LONG).show();
                    return;
                }
                if (arrList.get(6).Quantity == 0)
                {
                    Toast.makeText(getApplicationContext(), "Sold Out !", Toast.LENGTH_LONG).show();
                    return;
                }
                cart.addToCart(cursor.getInt(0), arrList.get(6).ProdID, arrList.get(6).ProdName, arrList.get(6).Price, arrList.get(6).Quantity, arrList.get(6).CatID, R.drawable.ic_fashion_3);
                Toast.makeText(getApplicationContext(), "Added !", Toast.LENGTH_LONG).show();
            }
        });

        Button btn8 = (Button)findViewById(R.id.addCartBtn_8);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!cursor.moveToFirst()) || cursor.getString(4).equals("Out"))
                {
                    Toast.makeText(getApplicationContext(), "Please login firstly !", Toast.LENGTH_LONG).show();
                    return;
                }
                if (arrList.get(7).Quantity == 0)
                {
                    Toast.makeText(getApplicationContext(), "Sold Out !", Toast.LENGTH_LONG).show();
                    return;
                }
                cart.addToCart(cursor.getInt(0), arrList.get(7).ProdID, arrList.get(7).ProdName, arrList.get(7).Price, arrList.get(7).Quantity, arrList.get(7).CatID, R.drawable.ic_fashion_4);
                Toast.makeText(getApplicationContext(), "Added !", Toast.LENGTH_LONG).show();
            }
        });

    }
}
