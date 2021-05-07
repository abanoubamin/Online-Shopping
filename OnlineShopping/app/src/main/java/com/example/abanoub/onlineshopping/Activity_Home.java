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

public class Activity_Home extends AppCompatActivity {

    ECommerceDBHelper cart;
    Cursor cursor;

    Products prods;
    ArrayList<Products> arrList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__home);

        cart = new ECommerceDBHelper(getApplicationContext());
        cursor = cart.startUp();

        prods = new Products();
        arrList = new ArrayList<Products>();
        arrList = prods.fetchAllProducts();

        Button btn1 = (Button)findViewById(R.id.addCartBtn_1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!cursor.moveToFirst()) || cursor.getString(4).equals("Out"))
                {
                    Toast.makeText(getApplicationContext(), "Please login firstly !", Toast.LENGTH_LONG).show();
                    return;
                }
                if (arrList.get(0).Quantity == 0)
                {
                    Toast.makeText(getApplicationContext(), "Sold Out !", Toast.LENGTH_LONG).show();
                    return;
                }
                cart.addToCart(cursor.getInt(0), arrList.get(0).ProdID, arrList.get(0).ProdName, arrList.get(0).Price, arrList.get(0).Quantity, arrList.get(0).CatID, R.drawable.ic_home_1);
                Toast.makeText(getApplicationContext(), "Added !", Toast.LENGTH_LONG).show();
            }
        });

        Button btn2 = (Button)findViewById(R.id.addCartBtn_2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!cursor.moveToFirst()) || cursor.getString(4).equals("Out"))
                {
                    Toast.makeText(getApplicationContext(), "Please login firstly !", Toast.LENGTH_LONG).show();
                    return;
                }
                if (arrList.get(1).Quantity == 0)
                {
                    Toast.makeText(getApplicationContext(), "Sold Out !", Toast.LENGTH_LONG).show();
                    return;
                }
                cart.addToCart(cursor.getInt(0), arrList.get(1).ProdID, arrList.get(1).ProdName, arrList.get(1).Price, arrList.get(1).Quantity, arrList.get(1).CatID, R.drawable.ic_home_2);
                Toast.makeText(getApplicationContext(), "Added !", Toast.LENGTH_LONG).show();
            }
        });

        Button btn3 = (Button)findViewById(R.id.addCartBtn_3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!cursor.moveToFirst()) || cursor.getString(4).equals("Out"))
                {
                    Toast.makeText(getApplicationContext(), "Please login firstly !", Toast.LENGTH_LONG).show();
                    return;
                }
                if (arrList.get(2).Quantity == 0)
                {
                    Toast.makeText(getApplicationContext(), "Sold Out !", Toast.LENGTH_LONG).show();
                    return;
                }
                cart.addToCart(cursor.getInt(0), arrList.get(2).ProdID, arrList.get(2).ProdName, arrList.get(2).Price, arrList.get(2).Quantity, arrList.get(2).CatID, R.drawable.ic_home_3);
                Toast.makeText(getApplicationContext(), "Added !", Toast.LENGTH_LONG).show();
            }
        });

        Button btn4 = (Button)findViewById(R.id.addCartBtn_4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!cursor.moveToFirst()) || cursor.getString(4).equals("Out"))
                {
                    Toast.makeText(getApplicationContext(), "Please login firstly !", Toast.LENGTH_LONG).show();
                    return;
                }
                if (arrList.get(3).Quantity == 0)
                {
                    Toast.makeText(getApplicationContext(), "Sold Out !", Toast.LENGTH_LONG).show();
                    return;
                }
                cart.addToCart(cursor.getInt(0), arrList.get(3).ProdID, arrList.get(3).ProdName, arrList.get(3).Price, arrList.get(3).Quantity, arrList.get(3).CatID, R.drawable.ic_home_4);
                Toast.makeText(getApplicationContext(), "Added !", Toast.LENGTH_LONG).show();
            }
        });

    }
}
