package com.vikas.myproject1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class Payment_Order_Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "derails.db";
    public static final String TABLE_NAME = "payment_details";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "TOTAL_PRICE";
    public static final String COL_3 = "PAYMENT_TYPE";
    public static final String COL_4 = "BANK_NAME";
    public static final String COL_5 = "BRANCH";
    public static final String COL_7 = "CARD_NO";
    public static final String COL_8 = "CVV_NO";


    public Payment_Order_Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,TOTAL_PRICE INTEGER,PAYMENT_TYPE TEXT,BANK_NAME TEXT,BRANCH TEXT,CARD_NO INTEGER,CVV_NO INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String total_price, String payment_type, Object bank_name, Object branch, String card_no, String vcc_no) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, total_price);
        contentValues.put(COL_3, payment_type);
        contentValues.put(COL_4, String.valueOf(bank_name));
        contentValues.put(COL_5, String.valueOf(branch));
        contentValues.put(COL_7, card_no);
        contentValues.put(COL_8, vcc_no);
        long result = db.insert(TABLE_NAME, null, contentValues);

        //   db.execSQL("INSERT INTO TABLE_NAME VALUES('admin','admin');");
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }

    public Cursor getAllData1(Object CATEGORY_NAME) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where CATEGORY_NAME =?", new String[]{String.valueOf(CATEGORY_NAME)});

        return res;
    }

    public Cursor getAllItemDetail(String NAME) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where NAME =?", new String[]{NAME});

        return res;
    }

    public ArrayList<HashMap<String, String>> GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT  * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();

            user.put("total_price",cursor.getString(1)+"");
            user.put("payment",cursor.getString(2)+"");
            user.put("bankname",cursor.getString(3)+"");
            user.put("branch",cursor.getString(4)+"");
            user.put("cardno",cursor.getString(5)+"");
            user.put("cvv",cursor.getString(6)+"");
            userList.add(user);
        }
        return  userList;
    }
}

