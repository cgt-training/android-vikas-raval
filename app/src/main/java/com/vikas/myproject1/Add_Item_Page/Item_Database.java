package com.vikas.myproject1.Add_Item_Page;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by vikas
 */
public class Item_Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "item.db";
    public static final String TABLE_NAME = "item_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "DESCRIPTION";
    public static final String COL_4 = "PRICE";
    public static final String COL_5 = "QUANTITY";
    public static final String COL_6 = "IMAGE";
    public static final String COL_7 = "SIZE";
    public static final String COL_8 = "CATEGORY_NAME";


    public Item_Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,DESCRIPTION TEXT,PRICE INTEGER,QUANTITY INTEGER,IMAGE BLOB,SIZE INTEGER,CATEGORY_NAME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String description, String price, String quantity, byte[] image, Object size, Object cat_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,description);
        contentValues.put(COL_4,price);
        contentValues.put(COL_5,quantity);
        contentValues.put(COL_6,image);
        contentValues.put(COL_7, (String) size);
        contentValues.put(COL_8, (String) cat_name);
        long result = db.insert(TABLE_NAME,null ,contentValues);

        //   db.execSQL("INSERT INTO TABLE_NAME VALUES('admin','admin');");
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

    public Cursor getAllData1(Object CATEGORY_NAME) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME+" where CATEGORY_NAME =?", new String[]{String.valueOf(CATEGORY_NAME)});

        return res;
    }

    public Cursor getAllItemDetail(Object NAME) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME+" where NAME =?", new String[]{String.valueOf(NAME)});

        return res;
    }

    public boolean updateData1(String val1,String val2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_1,pass1);
        contentValues.put(COL_5,val2);
        db.update(TABLE_NAME, contentValues, "NAME = ?",new String[] { val1 });
        return true;
    }

public ArrayList<HashMap<String, String>> GetUsers(){
    SQLiteDatabase db = this.getWritableDatabase();
    ArrayList<HashMap<String, String>> userList = new ArrayList<>();
    String query = "SELECT  * FROM " + TABLE_NAME;
    Cursor cursor = db.rawQuery(query,null);
    while (cursor.moveToNext()){
        HashMap<String,String> user = new HashMap<>();
        byte[] bb = cursor.getBlob(5);

        String temp=Base64.encodeToString(bb, Base64.DEFAULT);

        user.put("name",cursor.getString(1));
        user.put("price",cursor.getString(3));
//            user.put("image", String.valueOf(cursor.getBlob(5)));
        user.put("image", temp);
        userList.add(user);
    }
    return  userList;
}

    public List<String> getAllLabels(){
        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }

}
