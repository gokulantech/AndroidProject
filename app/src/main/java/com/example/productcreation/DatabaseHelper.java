package com.example.productcreation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String my_db = "ProductTodo.db";
    public static final String sql1 = "create Table userdetails(email TEXT primary key,user TEXT,password TEXT,phone TEXT)";
    public static final String sql2 = "create Table products(productId TEXT primary key,productName TEXT,description TEXT)";

    DatabaseHelper(@Nullable Context context) {
        super(context, "productTodo.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL(sql1);
        MyDB.execSQL(sql2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists userdetails");
        MyDB.execSQL("drop Table if exists products");
        onCreate(MyDB);
    }

    public Boolean insertData(String email, String user, String password, String phone) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("user", user);
        contentValues.put("password", password);
        contentValues.put("phone", phone);
        long result = MyDB.insert("userdetails", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public boolean checkMail(String email) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from userdetails where email=?", new String[]{email});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkMailAndPassword(String email, String user, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from userdetails where email=? and user=? and password=?", new String[]{email, user, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean addProduct(String productId, String productName, String description) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("productId", productId);
        contentValues.put("productName", productName);
        contentValues.put("description", description);
        long result = MyDB.insert("products", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    Cursor readAllData() {
        String query = " select * from products";
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = null;
        if (MyDB != null) {
            cursor = MyDB.rawQuery(query, null);
        }
        return cursor;

    }

    boolean updateData(String product_id, String product_name, String description) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put("productId", product_id);
        con.put("productName", product_name);
        con.put("description", description);
        long result = MyDB.update("products", con, "productId=?", new String[]{product_id});
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    boolean deleteData(String product_id){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        long result = MyDB.delete("products","productId=?",new String[]{product_id});
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from products");
    }

}
