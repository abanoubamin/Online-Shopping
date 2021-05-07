package com.example.abanoub.onlineshopping;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Register extends AppCompatActivity {

    //ECommerceDBHelper customer;

    EditText birthdayTxt;
    int year_x, month_x, day_x;
    String dateString;
    static final int DIALOG_ID = 0;

    DatabaseReference dbRef;
    customer cstmr;
    ArrayList<customer> arrList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //customer = new ECommerceDBHelper(getApplicationContext());

        final EditText nameTxt = (EditText)findViewById(R.id.editText);
        final EditText usernameTxt = (EditText)findViewById(R.id.editText2);
        final EditText passwordTxt = (EditText)findViewById(R.id.editText3);

        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);
        showDialogOnClick();

        final EditText jobTxt = (EditText)findViewById(R.id.editText5);

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

        Button rgsBtn = (Button)findViewById(R.id.button);
        rgsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameTxt.getText().toString().equals("") || usernameTxt.getText().toString().equals("") || passwordTxt.getText().toString().equals("") || birthdayTxt.getText().toString().equals("") || jobTxt.getText().toString().equals(""))
                {
                    Toast.makeText(Register.this, "Please Enter ALL the Required Information", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!(android.util.Patterns.EMAIL_ADDRESS.matcher(usernameTxt.getText().toString()).matches()))
                {
                    Toast.makeText(Register.this, "Please enter a valid username (Email)", Toast.LENGTH_LONG).show();
                    return;
                }

                /*Cursor cursor = customer.login(usernameTxt.getText().toString());
                if(cursor.moveToFirst())
                {
                    Toast.makeText(Register.this, "This username (Email) is taken", Toast.LENGTH_LONG).show();
                    return;
                }*/

                boolean flag = false;
                for (int i = 0; i < arrList.size(); i++)
                {
                    if (arrList.get(i).Username.equals(usernameTxt.getText().toString()))
                    {
                        flag = true;
                    }
                }
                if (flag == true)
                {
                    Toast.makeText(Register.this, "This username (Email) is taken", Toast.LENGTH_LONG).show();
                    return;
                }

                RadioButton male = (RadioButton)findViewById(R.id.radioButton);
                RadioButton female = (RadioButton)findViewById(R.id.radioButton2);
                String genderTxt = "";
                if(male.isChecked())
                {
                    genderTxt = "Male";
                }
                else if(female.isChecked())
                {
                    genderTxt = "Female";
                }
                if(genderTxt.equals(""))
                {
                    Toast.makeText(Register.this, "Please choose your gender", Toast.LENGTH_LONG).show();
                    return;
                }

                //customer.register(nameTxt.getText().toString(), usernameTxt.getText().toString(), passwordTxt.getText().toString(), genderTxt, dateString, jobTxt.getText().toString());

                if (arrList.size() == 0)
                {
                    cstmr = new customer(1, nameTxt.getText().toString(), usernameTxt.getText().toString(), passwordTxt.getText().toString(), genderTxt, dateString, jobTxt.getText().toString());
                    dbRef.child("customer").child(String.valueOf(1)).setValue(cstmr);
                }
                else
                {
                    cstmr = new customer(arrList.size() + 1, nameTxt.getText().toString(), usernameTxt.getText().toString(), passwordTxt.getText().toString(), genderTxt, dateString, jobTxt.getText().toString());
                    dbRef.child("customer").child(String.valueOf(arrList.size() + 1)).setValue(cstmr);
                }

                Toast.makeText(Register.this, "Successful SignUp", Toast.LENGTH_LONG).show();
                Intent login = new Intent(Register.this, Login.class);
                login.putExtra("username", usernameTxt.getText().toString());
                login.putExtra("password", passwordTxt.getText().toString());
                startActivity(login);
            }
        });

        Button loginBtn = (Button)findViewById(R.id.button2);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(Register.this, Login.class);
                login.putExtra("username", usernameTxt.getText().toString());
                login.putExtra("password", passwordTxt.getText().toString());
                startActivity(login);
            }
        });

    }

    public void showDialogOnClick() {
        birthdayTxt = (EditText)findViewById(R.id.editText4);
        birthdayTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID)
            return new DatePickerDialog(this, dpickerListener, year_x,month_x,day_x);
        return null;
        //return super.onCreateDialog(id);
    }

    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_x = year;
            month_x = month + 1;
            day_x = dayOfMonth;
            dateString = year_x + "-" + month_x + "-" + day_x;
            if(month_x < 10)
                dateString = year_x + "-0" + month_x + "-" + day_x;
            if(day_x < 10)
                dateString = year_x + "-" + month_x + "-0" + day_x;
            if(month_x < 10 && day_x < 10)
                dateString = year_x + "-0" + month_x + "-0" + day_x;
            Toast.makeText(Register.this, dateString, Toast.LENGTH_LONG).show();
            birthdayTxt.setText(dateString);
        }
    };

}
