package com.example.abanoub.onlineshopping;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Activity_Search extends AppCompatActivity {

    String queryString;
    ArrayList<Products> searchArrList;
    ArrayList<Products> searchArrList2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__search);

        queryString = getIntent().getExtras().getString("Query");

        Products prods = new Products();
        searchArrList = new ArrayList<Products>();
        searchArrList = prods.fetchAllProducts();

        searchArrList2 = new ArrayList<Products>();
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < searchArrList.size(); i++)
                {
                    if (searchArrList.get(i).ProdName.toLowerCase().contains(queryString.toLowerCase()))
                    {
                        searchArrList2.add(searchArrList.get(i));
                    }
                }
            }
        },2000);

        Handler h1 = new Handler();
        h1.postDelayed(new Runnable() {
            @Override
            public void run() {
                ListView list = (ListView)findViewById(R.id.listView2);
                list.setAdapter(new ListAdapter(getApplicationContext(), searchArrList2));
            }
        },2000);

    }

}

class ListAdapter extends BaseAdapter {

    Context context;
    ArrayList<Products> arrList;

    ListAdapter(Context context, ArrayList<Products> searchArrList) {
        this.context = context;
        this.arrList = searchArrList;
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
        final View listItem = layoutInflater.inflate(R.layout.layout_list, parent, false);
        ImageView image = (ImageView) listItem.findViewById(R.id.imageView);
        TextView name = (TextView) listItem.findViewById(R.id.nameTextView);
        final TextView price = (TextView) listItem.findViewById(R.id.priceTextView);
        final Products prod = arrList.get(position);
        image.setImageResource(prod.ImgID);
        name.setText(prod.ProdName);
        price.setText("EGP " + prod.Price);
        Button addBtn = (Button) listItem.findViewById(R.id.button);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ECommerceDBHelper cart = new ECommerceDBHelper(context);
                Cursor cursor = cart.startUp();
                if ((!cursor.moveToFirst()) || cursor.getString(4).equals("Out"))
                {
                    Toast.makeText(context, "Please login firstly !", Toast.LENGTH_LONG).show();
                    return;
                }
                if (arrList.get(0).Quantity == 0)
                {
                    Toast.makeText(context, "Sold Out !", Toast.LENGTH_LONG).show();
                    return;
                }
                cart.addToCart(cursor.getInt(0), prod.ProdID, prod.ProdName, prod.Price, prod.Quantity, prod.CatID, prod.ImgID);
                Toast.makeText(context, "Added !", Toast.LENGTH_LONG).show();
            }
        });
        return listItem;
    }

}