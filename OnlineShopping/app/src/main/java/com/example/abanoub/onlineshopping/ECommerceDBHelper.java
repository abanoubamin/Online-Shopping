package com.example.abanoub.onlineshopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ECommerceDBHelper extends SQLiteOpenHelper {

    private static String DBName = "ECommerceDB";
    SQLiteDatabase DB;

    public ECommerceDBHelper (Context context)
    {
        super(context, DBName, null, 28);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table recentCustomer(CustID integer PRIMARY KEY, Username text not null, Password text not null, RememberMe text not null, SignIn text not null)");
        db.execSQL("create table myCart(CustID integer not null, ProdID integer not null, ProdName text not null, Price integer not null, Quantity integer not null, CatID integer not null, ImgID integer, PRIMARY KEY(CustID, ProdID), FOREIGN KEY(CustID) REFERENCES recentCustomer (CustID))");
        db.execSQL("create table totalOrder(CustID integer not null, ProdID integer not null, ProdName text not null, cartPrice integer not null, cartQuantity integer not null, CatID integer not null, ImgID integer, PRIMARY KEY(CustID, ProdID), FOREIGN KEY(CustID) REFERENCES recentCustomer (CustID))");

        /*db.execSQL("create table customer(CustID integer primary key autoincrement, CustName text not null, Username text not null, Password text not null, Gender text not null, Birthday text not null, Job text not null)");
        db.execSQL("create table Orders(OrdID integer primary key autoincrement, OrdDate text not null, CustID integer not null, Address text not null, FOREIGN KEY(CustID) REFERENCES customer (CustID))");
        db.execSQL("create table orderDetails(OrdID integer not null, ProdID integer not null, Quantity integer not null, PRIMARY KEY(OrdID, ProdID), FOREIGN KEY(OrdID) REFERENCES Orders (OrdID), FOREIGN KEY(ProdID) REFERENCES Products (ProdID))");
        db.execSQL("create table Products(ProdID integer primary key autoincrement, ProdName text not null, Price integer not null, Quantity integer not null, CatID integer not null, FOREIGN KEY(CatID) REFERENCES category (CatID))");
        db.execSQL("create table category(CatID integer primary key autoincrement, CatName text not null)");

        db.execSQL("insert into category (CatName) values ('HOME')");
        db.execSQL("insert into category (CatName) values ('FASHION')");
        db.execSQL("insert into category (CatName) values ('PHONES')");
        db.execSQL("insert into category (CatName) values ('LAPTOPS')");
        db.execSQL("insert into category (CatName) values ('ELECTRONICS')");
        db.execSQL("insert into category (CatName) values ('AUTOMOBILE')");


        db.execSQL("insert into Products (ProdName, Price, Quantity, CatID) values ('Jac NGM - 2005 Microwave Oven - 20L', 999, 1, 1)");
        db.execSQL("insert into Products (ProdName, Price, Quantity, CatID) values ('Jac NGH-329 Oil Heater – 9 Fins - 1500 W', 549, 1, 1)");
        db.execSQL("insert into Products (ProdName, Price, Quantity, CatID) values ('Tank Water Filter Tank Power 5 Stage + 2X TP1 FREE', 599, 1, 1)");
        db.execSQL("insert into Products (ProdName, Price, Quantity, CatID) values ('Art Home Office Chair - Medical Chair', 1999, 1, 1)");

        db.execSQL("insert into Products (ProdName, Price, Quantity, CatID) values ('Fly Star Men Lace Up Tri-Tone Sneakers - Navy Blue', 549, 2, 2)");
        db.execSQL("insert into Products (ProdName, Price, Quantity, CatID) values ('Andora Quilted Waterproof Vest - Blue', 399, 2, 2)");
        db.execSQL("insert into Products (ProdName, Price, Quantity, CatID) values ('Winner 2018 WINNER Mens Watches Mechanical', 430, 2, 2)");
        db.execSQL("insert into Products (ProdName, Price, Quantity, CatID) values ('AIO Bag Backback Power Show Lab - Black', 299, 2, 2)");

        db.execSQL("insert into Products (ProdName, Price, Quantity, CatID) values ('Xiaomi Redmi 6 - 5.45-inch 64GB - 4G Mobile Phone - Blue', 2999, 3, 3)");
        db.execSQL("insert into Products (ProdName, Price, Quantity, CatID) values ('Apple iPhone X - 64GB - Space Gray', 18999, 3, 3)");
        db.execSQL("insert into Products (ProdName, Price, Quantity, CatID) values ('honor 8X - 6.5-inch 64GB Dual SIM 4G Mobile Phone - Blue', 4444, 3, 3)");
        db.execSQL("insert into Products (ProdName, Price, Quantity, CatID) values ('Infinix X608 Hot 6 Pro - 6.0-inch 16GB Mobile Phone - Magic Gold', 1999, 3, 3)");

        db.execSQL("insert into Products (ProdName, Price, Quantity, CatID) values ('Dell Inspiron 15-7559', 21999, 4, 4)");
        db.execSQL("insert into Products (ProdName, Price, Quantity, CatID) values ('Lenovo Ideapad 110-15IBR', 4699, 4, 4)");
        db.execSQL("insert into Products (ProdName, Price, Quantity, CatID) values ('HP 15-ay016nx', 9999, 4, 4)");
        db.execSQL("insert into Products (ProdName, Price, Quantity, CatID) values ('Lenovo Ideapad 300-15IBR', 5999, 4, 4)");

        db.execSQL("insert into Products (ProdName, Price, Quantity, CatID) values ('ATA 55-inch Ultra HD 4K Smart Monitor', 6389, 5, 5)");
        db.execSQL("insert into Products (ProdName, Price, Quantity, CatID) values ('MediaTech Subwoofer 2.1 - With Remote Control', 1449, 5, 5)");
        db.execSQL("insert into Products (ProdName, Price, Quantity, CatID) values ('Canon EOS 1300D', 6220, 5, 5)");
        db.execSQL("insert into Products (ProdName, Price, Quantity, CatID) values ('Sony PlayStation 4 Slim - 500GB Gaming Console - Black', 6999, 5, 5)");

        db.execSQL("insert into Products (ProdName, Price, Quantity, CatID) values ('Generic Air Compressor - 12V - 1 Cylinder - Silver/Black', 246, 6, 6)");
        db.execSQL("insert into Products (ProdName, Price, Quantity, CatID) values ('Pioneer Mvh-S305Bt - Car Audio Stereo', 1740, 6, 6)");
        db.execSQL("insert into Products (ProdName, Price, Quantity, CatID) values ('H4 Night Breaker Laser Head Lamp Kit - 2 Pcs', 400, 6, 6)");
        db.execSQL("insert into Products (ProdName, Price, Quantity, CatID) values ('EZI Ezi Extra Power Lube - 326ml Blue', 155, 6, 6)");*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*db.execSQL("drop table if exists orderDetails");
        db.execSQL("drop table if exists Orders");
        db.execSQL("drop table if exists customer");
        db.execSQL("drop table if exists Products");
        db.execSQL("drop table if exists category");*/
        db.execSQL("drop table if exists recentCustomer");
        db.execSQL("drop table if exists myCart");
        db.execSQL("drop table if exists totalOrder");
        onCreate(db);
    }

    public Cursor startUp()
    {
        DB = getReadableDatabase();
        Cursor cursor = DB.rawQuery("Select * from recentCustomer", null);
        if (cursor != null)
            cursor.moveToFirst();
        DB.close();
        return cursor;
    }

    public void ReMe(int custID, String username, String password, String status, String log)
    {
        DB = getWritableDatabase();
        DB.execSQL("drop table if exists recentCustomer");
        DB.execSQL("create table recentCustomer(CustID integer PRIMARY KEY, Username text not null, Password text not null, RememberMe text not null, SignIn text not null)");
        ContentValues row = new ContentValues();
        row.put("CustID", custID);
        row.put("Username", username);
        row.put("Password", password);
        row.put("RememberMe", status);
        row.put("SignIn", log);
        DB.insert("recentCustomer", null, row);
        DB.close();
    }

    public Cursor getMyCart(int custID)
    {
        DB = getReadableDatabase();
        String[] arg = {String.valueOf(custID)};
        Cursor cursor = DB.rawQuery("Select * from myCart where CustID = ?", arg);
        if (cursor != null)
            cursor.moveToFirst();
        DB.close();
        return cursor;
    }

    public Cursor getTotalOrder(int custID)
    {
        DB = getReadableDatabase();
        String[] arg = {String.valueOf(custID)};
        Cursor cursor = DB.rawQuery("Select * from totalOrder where CustID = ?", arg);
        if (cursor != null)
            cursor.moveToFirst();
        DB.close();
        return cursor;
    }

    public Cursor getTotalOrderVer2(int prodID)
    {
        DB = getReadableDatabase();
        String[] arg = {String.valueOf(prodID)};
        Cursor cursor = DB.rawQuery("Select * from totalOrder where ProdID = ?", arg);
        if (cursor != null)
            cursor.moveToFirst();
        DB.close();
        return cursor;
    }

    public void addToCart(int custID, int prodID, String prodName, int price, int quantity, int catID, int imgID)
    {
        DB = getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("CustID", custID);
        row.put("ProdID", prodID);
        row.put("ProdName", prodName);
        row.put("Price", price);
        row.put("Quantity", quantity);
        row.put("CatID", catID);
        row.put("ImgID", imgID);
        DB.insert("myCart", null, row);
        ContentValues row1 = new ContentValues();
        row1.put("CustID", custID);
        row1.put("ProdID", prodID);
        row1.put("ProdName", prodName);
        row1.put("cartPrice", price);
        row1.put("cartQuantity", 1);
        row1.put("CatID", catID);
        row1.put("ImgID", imgID);
        DB.insert("totalOrder", null, row1);
        DB.close();
    }

    public void removeFromCart(int custID)
    {
        DB = getWritableDatabase();
        DB.delete("myCart", "CustID = '" + custID + "'", null);
        DB.delete("totalOrder", "CustID = '" + custID + "'", null);
        DB.close();
    }

    public void removeFromCartVer2(String prodName)
    {
        DB = getWritableDatabase();
        DB.delete("myCart", "ProdName = '" + prodName + "'", null);
        DB.delete("totalOrder", "ProdName = '" + prodName + "'", null);
        DB.close();
    }

    public void updateQuantity(String prodName, int newCartPrice, int newCartQuantity)
    {
        DB = getWritableDatabase();
        String upd="Update totalOrder set cartPrice=?, cartQuantity=? where ProdName like ?";
        DB.execSQL(upd, new String[]{String.valueOf(newCartPrice), String.valueOf(newCartQuantity), prodName});
        DB.close();
    }

    /*public void register(String custName, String username, String password, String gender, String birthday, String job)
    {
        ContentValues row = new ContentValues();
        row.put("CustName", custName);
        row.put("Username", username);
        row.put("Password", password);
        row.put("Gender", gender);
        row.put("Birthday", birthday);
        row.put("Job", job);
        DB = getWritableDatabase();
        DB.insert("customer", null, row);
        DB.close();
    }

    public Cursor login(String username)
    {
        DB = getReadableDatabase();
        String[] arg = {username};
        Cursor cursor = DB.rawQuery("Select Username, Password from customer where Username like ?", arg);
        if (cursor != null)
            cursor.moveToFirst();
        DB.close();
        return cursor;
    }*/

}