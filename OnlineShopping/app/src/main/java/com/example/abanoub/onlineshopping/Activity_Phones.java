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

public class Activity_Phones extends AppCompatActivity {

    ECommerceDBHelper cart;
    Cursor cursor;

    Products prods;
    ArrayList<Products> arrList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__phones);

        cart = new ECommerceDBHelper(getApplicationContext());
        cursor = cart.startUp();

        prods = new Products();
        arrList = new ArrayList<Products>();
        arrList = prods.fetchAllProducts();

        Button btn9 = (Button)findViewById(R.id.addCartBtn_9);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!cursor.moveToFirst()) || cursor.getString(4).equals("Out"))
                {
                    Toast.makeText(getApplicationContext(), "Please login firstly !", Toast.LENGTH_LONG).show();
                    return;
                }
                if (arrList.get(8).Quantity == 0)
                {
                    Toast.makeText(getApplicationContext(), "Sold Out !", Toast.LENGTH_LONG).show();
                    return;
                }
                cart.addToCart(cursor.getInt(0), arrList.get(8).ProdID, arrList.get(8).ProdName, arrList.get(8).Price, arrList.get(8).Quantity, arrList.get(8).CatID, R.drawable.ic_phones_1);
                Toast.makeText(getApplicationContext(), "Added !", Toast.LENGTH_LONG).show();
            }
        });

        Button btn10 = (Button)findViewById(R.id.addCartBtn_10);
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!cursor.moveToFirst()) || cursor.getString(4).equals("Out"))
                {
                    Toast.makeText(getApplicationContext(), "Please login firstly !", Toast.LENGTH_LONG).show();
                    return;
                }
                if (arrList.get(9).Quantity == 0)
                {
                    Toast.makeText(getApplicationContext(), "Sold Out !", Toast.LENGTH_LONG).show();
                    return;
                }
                cart.addToCart(cursor.getInt(0), arrList.get(9).ProdID, arrList.get(9).ProdName, arrList.get(9).Price, arrList.get(9).Quantity, arrList.get(9).CatID, R.drawable.ic_phones_2);
                Toast.makeText(getApplicationContext(), "Added !", Toast.LENGTH_LONG).show();
            }
        });

        Button btn11 = (Button)findViewById(R.id.addCartBtn_11);
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!cursor.moveToFirst()) || cursor.getString(4).equals("Out"))
                {
                    Toast.makeText(getApplicationContext(), "Please login firstly !", Toast.LENGTH_LONG).show();
                    return;
                }
                if (arrList.get(10).Quantity == 0)
                {
                    Toast.makeText(getApplicationContext(), "Sold Out !", Toast.LENGTH_LONG).show();
                    return;
                }
                cart.addToCart(cursor.getInt(0), arrList.get(10).ProdID, arrList.get(10).ProdName, arrList.get(10).Price, arrList.get(10).Quantity, arrList.get(10).CatID, R.drawable.ic_phones_3);
                Toast.makeText(getApplicationContext(), "Added !", Toast.LENGTH_LONG).show();
            }
        });

        Button btn12 = (Button)findViewById(R.id.addCartBtn_12);
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!cursor.moveToFirst()) || cursor.getString(4).equals("Out"))
                {
                    Toast.makeText(getApplicationContext(), "Please login firstly !", Toast.LENGTH_LONG).show();
                    return;
                }
                if (arrList.get(11).Quantity == 0)
                {
                    Toast.makeText(getApplicationContext(), "Sold Out !", Toast.LENGTH_LONG).show();
                    return;
                }
                cart.addToCart(cursor.getInt(0), arrList.get(11).ProdID, arrList.get(11).ProdName, arrList.get(11).Price, arrList.get(11).Quantity, arrList.get(11).CatID, R.drawable.ic_phones_4);
                Toast.makeText(getApplicationContext(), "Added !", Toast.LENGTH_LONG).show();
            }
        });

    }
}
