package com.example.abanoub.onlineshopping;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    ECommerceDBHelper customer;

    DatabaseReference dbRef;
    customer cstmr;
    ArrayList<customer> arrList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        customer = new ECommerceDBHelper(getApplicationContext());

        final EditText usernameTxt = (EditText)findViewById(R.id.editText6);
        final EditText passwordTxt = (EditText)findViewById(R.id.editText7);
        usernameTxt.setText(getIntent().getExtras().getString("username"));
        passwordTxt.setText(getIntent().getExtras().getString("password"));

        FirebaseDatabase fbDb = FirebaseDatabase.getInstance();
        dbRef = fbDb.getReference("OnShopDb");
        cstmr = new customer();
        arrList = new ArrayList<customer>();
        dbRef.child("customer").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dSnap : dataSnapshot.getChildren())
                {
                    cstmr = dSnap.getValue(customer.class);
                    arrList.add(cstmr);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Button frgtPassBtn = (Button)findViewById(R.id.button3);
        frgtPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Cursor cursor = customer.login(usernameTxt.getText().toString());
                JavaMail mail = new JavaMail(getApplicationContext(),usernameTxt.getText().toString(),"Password Recovery","Password: " + cursor.getString(1));
                mail.execute();*/

                String password = "";
                for (int i = 0; i < arrList.size(); i++)
                {
                    if (arrList.get(i).Username.equals(usernameTxt.getText().toString()))
                    {
                        password = arrList.get(i).Password;
                    }
                }
                if (! password.equals(""))
                {
                    JavaMail mail = new JavaMail(getApplicationContext(),usernameTxt.getText().toString(),"Password Recovery","Password: " + password);
                    mail.execute();
                    Toast.makeText(getApplicationContext(),"Password has been sent to: " + usernameTxt.getText().toString(),Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(Login.this, "Please enter a valid username", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button loginBtn = (Button)findViewById(R.id.button4);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usernameTxt.getText().toString().equals("") || passwordTxt.getText().toString().equals(""))
                {
                    Toast.makeText(Login.this, "Please Enter Username and Password", Toast.LENGTH_LONG).show();
                    return;
                }

                /*Cursor cursor = customer.login(usernameTxt.getText().toString());
                if(!cursor.moveToFirst())
                {
                    Toast.makeText(Login.this, "Please enter a valid username", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!cursor.getString(1).equals(passwordTxt.getText().toString()))
                {
                    Toast.makeText(Login.this, "Please enter the right password", Toast.LENGTH_LONG).show();
                    return;
                }*/

                String password = "";
                int custID = 0;
                for (int i = 0; i < arrList.size(); i++)
                {
                    if (arrList.get(i).Username.equals(usernameTxt.getText().toString()))
                    {
                        password = arrList.get(i).Password;
                        custID = arrList.get(i).CustID;
                    }
                }
                if (password.equals(""))
                {
                    Toast.makeText(Login.this, "Please enter a valid username", Toast.LENGTH_LONG).show();
                    return;
                }
                else if (! password.equals(passwordTxt.getText().toString()))
                {
                    Toast.makeText(Login.this, "Please enter the right password", Toast.LENGTH_LONG).show();
                    return;
                }

                CheckBox reMe = (CheckBox)findViewById(R.id.checkBox);
                if(reMe.isChecked())
                {
                    customer.ReMe(custID, usernameTxt.getText().toString(), passwordTxt.getText().toString(), "On", "In");
                }
                else
                {
                    customer.ReMe(custID, usernameTxt.getText().toString(), passwordTxt.getText().toString(), "Off", "In");
                }
                Toast.makeText(Login.this, "Successful SignIn", Toast.LENGTH_LONG).show();
                Intent main = new Intent(Login.this, MainActivity.class);
                startActivity(main);
            }
        });

        Button rgsBtn = (Button)findViewById(R.id.button5);
        rgsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(Login.this, Register.class);
                startActivity(register);
            }
        });

    }
}
