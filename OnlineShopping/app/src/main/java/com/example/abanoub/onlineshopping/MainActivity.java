package com.example.abanoub.onlineshopping;

import android.content.Intent;
import android.database.Cursor;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ECommerceDBHelper customer;
    Cursor cursor;

    RelativeLayout rel_home, rel_fashion, rel_phones, rel_laptops, rel_electronics, rel_automobile;

    MenuItem searchItem;
    SearchView searchView;
    int voiceCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rel_home = findViewById(R.id.rellay_home);
        rel_fashion = findViewById(R.id.rellay_fashion);
        rel_phones = findViewById(R.id.rellay_phones);
        rel_laptops = findViewById(R.id.rellay_laptops);
        rel_electronics = findViewById(R.id.rellay_electronics);
        rel_automobile = findViewById(R.id.rellay_automobile);

        rel_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity_Home.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        rel_fashion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity_Fashion.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        rel_phones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity_Phones.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        rel_laptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity_Laptops.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        rel_electronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity_Electronics.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        rel_automobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity_Automobile.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);

        searchItem = menu.findItem(R.id.app_bar_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent searchIntent = new Intent(MainActivity.this, Activity_Search.class);
                searchIntent.putExtra("Query", query);
                startActivity(searchIntent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == voiceCode && resultCode == RESULT_OK)
        {
            ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            searchView.setQuery(text.get(0), false);
            searchItem.expandActionView();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        customer = new ECommerceDBHelper(getApplicationContext());
        cursor = customer.startUp();
        switch (item.getItemId()){
            case R.id.iSound:
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                startActivityForResult(intent, voiceCode);
                return true;
            case R.id.iCam:
                Intent intent1 = new Intent(MainActivity.this, Activity_Barcode.class);
                startActivity(intent1);
                return true;
            case R.id.iCart:
                Intent myCart = new Intent(MainActivity.this, Activity_Cart.class);
                myCart.putExtra("Address", "");
                startActivity(myCart);
                return true;
            case R.id.iIn:
                if(!cursor.moveToFirst())
                {
                    Intent signIn = new Intent(MainActivity.this, Login.class);
                    signIn.putExtra("username", "");
                    signIn.putExtra("password", "");
                    startActivity(signIn);
                    return true;
                }
                else
                {
                    Intent signIn = new Intent(MainActivity.this, Login.class);
                    signIn.putExtra("username", cursor.getString(1));
                    signIn.putExtra("password", cursor.getString(2));
                    startActivity(signIn);
                    return true;
                }
            case R.id.iOut:
                customer.ReMe(cursor.getInt(0), cursor.getString(1), cursor.getString(2), "Off", "Out");
                Toast.makeText(MainActivity.this, "Successful SignOut", Toast.LENGTH_LONG).show();
                return true;
        }
        return false;
        //return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem cart = menu.findItem(R.id.iCart);
        MenuItem in = menu.findItem(R.id.iIn);
        MenuItem out = menu.findItem(R.id.iOut);
        customer = new ECommerceDBHelper(getApplicationContext());
        cursor = customer.startUp();
        if(cursor.moveToFirst() && cursor.getString(4).equals("In"))
        {
            cart.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            cart.setVisible(true);
            in.setVisible(false);
            out.setVisible(true);
        }
        else
        {
            cart.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
            cart.setVisible(false);
            in.setVisible(true);
            out.setVisible(false);
        }
        return true;
        //return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        customer = new ECommerceDBHelper(getApplicationContext());
        cursor = customer.startUp();
        if (cursor.moveToFirst())
        {
            if (cursor.getString(3).equals("Off"))
            {
                customer.ReMe(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), "Out");
            }
        }
    }
}
