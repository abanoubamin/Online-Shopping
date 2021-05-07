package com.example.abanoub.onlineshopping;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class Activity_Cart extends AppCompatActivity {

    ECommerceDBHelper cart;
    Cursor customerCursor;
    Cursor cartCursor;
    Cursor cartCursor1;

    Calendar cal;
    int ordYear, ordMonth, ordDay;
    String ordDate, address, ordDetails;

    DatabaseReference dbRef;
    Orders ord;
    orderDetails details;
    ArrayList<Orders> ordArrList;
    ArrayList<orderDetails> detailsArrList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__cart);

        ListView list = (ListView)findViewById(R.id.listView);
        list.setAdapter(new MyListAdapter(this));

        cart = new ECommerceDBHelper(getApplicationContext());
        customerCursor = cart.startUp();
        cartCursor = cart.getTotalOrder(customerCursor.getInt(0));
        final TextView textTotal = (TextView)findViewById(R.id.txtTotal);
        final TextView textEGP = (TextView)findViewById(R.id.txtEGP);
        final ImageView placeImg = (ImageView)findViewById(R.id.imgMyPlace);
        final Button orderBtn = (Button) findViewById(R.id.orderButton);

        cal = Calendar.getInstance();
        ordYear  = cal.get(Calendar.YEAR);
        ordMonth = cal.get(Calendar.MONTH);
        ordDay = cal.get(Calendar.DAY_OF_MONTH);
        ordDate = ordYear + "-" + ordMonth + "-" + ordDay;
        if(ordMonth < 10)
            ordDate = ordYear + "-0" + ordMonth + "-" + ordDay;
        if(ordDay < 10)
            ordDate = ordYear + "-" + ordMonth + "-0" + ordDay;
        if(ordMonth < 10 && ordDay < 10)
            ordDate = ordYear + "-0" + ordMonth + "-0" + ordDay;

        address = "";
        placeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent map = new Intent(Activity_Cart.this, MapsActivity.class);
                startActivity(map);
            }
        });
        address = getIntent().getExtras().getString("Address");

        if(!cartCursor.moveToFirst())
        {
            textTotal.setText("");
            textEGP.setText("");
            placeImg.setVisibility(View.INVISIBLE);
            orderBtn.setVisibility(View.INVISIBLE);
        }
        else
        {
            FirebaseDatabase fbDb = FirebaseDatabase.getInstance();
            dbRef = fbDb.getReference("OnShopDb");
            ord = new Orders();
            ordArrList = new ArrayList<Orders>();
            dbRef.child("Orders").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dSnap : dataSnapshot.getChildren())
                    {
                        ord = dSnap.getValue(Orders.class);
                        ordArrList.add(ord);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            details = new orderDetails();
            detailsArrList = new ArrayList<orderDetails>();
            dbRef.child("orderDetails").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dSnap : dataSnapshot.getChildren())
                    {
                        details = dSnap.getValue(orderDetails.class);
                        detailsArrList.add(details);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            int totalPrice = 0;
            while (!cartCursor.isAfterLast())
            {
                totalPrice += cartCursor.getInt(3);
                cartCursor.moveToNext();
            }
            textTotal.setText("Total");
            textEGP.setText("EGP " + String.valueOf(totalPrice));
            orderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cartCursor = cart.getTotalOrder(customerCursor.getInt(0));
                    cartCursor1 = cart.getMyCart(customerCursor.getInt(0));
                    if(!cartCursor.moveToFirst())
                    {
                        textTotal.setText("");
                        textEGP.setText("");
                        placeImg.setVisibility(View.INVISIBLE);
                        orderBtn.setVisibility(View.INVISIBLE);
                    }
                    else
                    {
                        int ordId;
                        if (ordArrList.size() == 0)
                        {
                            ord = new Orders(1, ordDate, customerCursor.getInt(0), address);
                            ordId = 1;
                        }
                        else
                        {
                            ord = new Orders(ordArrList.size() + 1, ordDate, customerCursor.getInt(0), address);
                            ordId = ordArrList.size() + 1;
                        }
                        dbRef.child("Orders").child(String.valueOf(ordId)).setValue(ord);
                        int totalCost = 0;
                        ordDetails = "Thank you for shopping" + "\n\n";
                        while (!cartCursor.isAfterLast())
                        {
                            if (detailsArrList.size() == 0)
                            {
                                details = new orderDetails(ordId, cartCursor.getInt(1), cartCursor.getInt(4));
                                dbRef.child("orderDetails").child(String.valueOf(1)).setValue(details);
                                detailsArrList.add(details);
                                Products prod = new Products(cartCursor.getInt(1), cartCursor.getString(2), cartCursor1.getInt(3), cartCursor1.getInt(4) - cartCursor.getInt(4), cartCursor.getInt(5), cartCursor.getInt(6));
                                dbRef.child("Products").child(String.valueOf(cartCursor.getInt(1))).setValue(prod);
                                totalCost += cartCursor.getInt(3);
                            }
                            else
                            {
                                details = new orderDetails(ordId, cartCursor.getInt(1), cartCursor.getInt(4));
                                dbRef.child("orderDetails").child(String.valueOf(detailsArrList.size() + 1)).setValue(details);
                                detailsArrList.add(details);
                                Products prod = new Products(cartCursor.getInt(1), cartCursor.getString(2), cartCursor1.getInt(3), cartCursor1.getInt(4) - cartCursor.getInt(4), cartCursor.getInt(5), cartCursor.getInt(6));
                                dbRef.child("Products").child(String.valueOf(cartCursor.getInt(1))).setValue(prod);
                                totalCost += cartCursor.getInt(3);
                            }
                            ordDetails += "Product Name: " + cartCursor.getString(2) + "\n" + "Price: " + String.valueOf(cartCursor.getInt(3)) + " EGP \n" + "Quantity: " + String.valueOf(cartCursor.getInt(4)) + "\n\n";
                            cartCursor.moveToNext();
                            cartCursor1.moveToNext();
                        }

                        cart.removeFromCart(customerCursor.getInt(0));
                        textEGP.setText(String.valueOf(totalCost));
                        ordDetails += "Total Price: " + String.valueOf(totalCost);
                        JavaMail mail = new JavaMail(getApplicationContext(),customerCursor.getString(1),"Online Shopping Order Details",ordDetails);
                        mail.execute();
                        Toast.makeText(getApplicationContext(),"Order Details has been sent to: " + customerCursor.getString(1),Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "Your Order is Done !", Toast.LENGTH_LONG).show();

                        Intent main = new Intent(Activity_Cart.this, MainActivity.class);
                        main.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        finish();
                        startActivity(main);

                        /*Handler h = new Handler();
                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent main = new Intent(Activity_Cart.this, MainActivity.class);
                                main.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                finish();
                                startActivity(main);
                            }
                        },10000);*/

                    }
                }
            });
        }

    }

}

class MyListAdapter extends BaseAdapter {

    Context context;
    ArrayList<Products> arrList;
    ECommerceDBHelper cart;
    Cursor customerCursor;
    Cursor cartCursor;

    MyListAdapter(Context context) {
        this.context = context;
        arrList = new ArrayList<Products>();
        cart = new ECommerceDBHelper(context);
        customerCursor = cart.startUp();
        cartCursor = cart.getMyCart(customerCursor.getInt(0));
        while (!cartCursor.isAfterLast())
        {
            arrList.add(new Products(cartCursor.getInt(1), cartCursor.getString(2), cartCursor.getInt(3), cartCursor.getInt(4), cartCursor.getInt(5), cartCursor.getInt(6)));
            cartCursor.moveToNext();
        }
    }

    @Override
    public Object getItem(int position) {
        return arrList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return arrList.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        final View listItem = layoutInflater.inflate(R.layout.layout_cart, parent, false);
        ImageView image = (ImageView) listItem.findViewById(R.id.imgView);
        TextView name = (TextView) listItem.findViewById(R.id.txtView);
        final TextView price = (TextView) listItem.findViewById(R.id.priceTxtView);
        final Products prod = arrList.get(position);
        image.setImageResource(prod.ImgID);
        name.setText(prod.ProdName);
        Cursor totalCursor = cart.getTotalOrderVer2(prod.ProdID);
        price.setText("EGP " + String.valueOf(totalCursor.getInt(3)));
        ElegantNumberButton elegantNumBtn = (ElegantNumberButton) listItem.findViewById(R.id.elegantNumberButton);
        elegantNumBtn.setRange(1, prod.Quantity);
        elegantNumBtn.setNumber(String.valueOf(totalCursor.getInt(4)));
        elegantNumBtn.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                if (oldValue < newValue)
                {
                    cart.updateQuantity(prod.ProdName, (prod.Price * newValue), newValue);
                    price.setText("EGP " + String.valueOf(prod.Price * newValue));
                }
                else
                {
                    cart.updateQuantity(prod.ProdName, (prod.Price * oldValue) - prod.Price, newValue);
                    price.setText("EGP " + String.valueOf((prod.Price * oldValue) - prod.Price));
                }
            }
        });
        Button rmvBtn = (Button) listItem.findViewById(R.id.removeButton);
        rmvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrList.remove(position);
                cart.removeFromCartVer2(prod.ProdName);
                notifyDataSetChanged();
            }
        });
        return listItem;
    }

}
