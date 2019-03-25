package com.vikas.myproject1.Item_Details;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
        import java.util.HashMap;
import java.util.List;

/**
 * Created by vikas
 */
public class Item_Detail_Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "detail.db";
    public static final String TABLE_NAME = "item_details";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "DESCRIPTION";
    public static final String COL_4 = "PRICE";
    public static final String COL_5 = "QUANTITY";
    public static final String COL_6 = "SIZE";
    public static final String COL_7 = "IMAGE";
    public static final String COL_8 = "CATEGORY_NAME";
    public static final String COL_9 = "EMAIL";



    public Item_Detail_Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,DESCRIPTION TEXT,PRICE INTEGER,QUANTITY INTEGER,SIZE INTEGER,IMAGE BLOB,CATEGORY_NAME TEXT,EMAIL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String description, String price, String quantity, String size,byte[] image,String cat_name,String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("Image Database", ""+image);
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, description);
        contentValues.put(COL_4, price);
        contentValues.put(COL_5, quantity);
        contentValues.put(COL_6, size);
        contentValues.put(COL_7, image);
        contentValues.put(COL_8, cat_name);
        contentValues.put(COL_9, email);

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

    public Cursor getAll(Object NAME) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where NAME =?", new String[]{String.valueOf(NAME)});

        return res;
    }

    public Cursor getAllItemDetail2(Object EMAIL) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME+" where EMAIL =?", new String[]{String.valueOf(EMAIL)});

        return res;
    }

//    public ArrayList<HashMap<String, String>> GetUsers(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
//        String query = "SELECT  * FROM " + TABLE_NAME;
//        Cursor cursor = db.rawQuery(query,null);
//        while (cursor.moveToNext()){
//            HashMap<String,String> user = new HashMap<>();
//
//            user.put("item_name","\t\t\t\t"+cursor.getString(1)+"\t\t\t\t\t\t\t\t\t\t\t");
//            user.put("price",cursor.getString(3)+"\t\t\t\t\t\t\t\t\t\t\t\t");
//            user.put("quantity",cursor.getString(4)+"\t\t\t\t\t\t\t\t\t\t\t\t\t");
//            userList.add(user);
//        }
//        return  userList;
//    }

    public ArrayList<HashMap<String, String>> GetUsers1(String e){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
//        String query = "SELECT  * FROM " + TABLE_NAME;
//        Cursor cursor = db.rawQuery(query,null);
        Cursor cursor = db.rawQuery(
                "select * from " + TABLE_NAME + " where EMAIL = ? ", new String[]{e});
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            byte[] bb = cursor.getBlob(6);

            String temp=Base64.encodeToString(bb, Base64.DEFAULT);

            user.put("name",cursor.getString(1));
            user.put("price",cursor.getString(3));
            user.put("quantity",cursor.getString(4));
            user.put("size",cursor.getString(5));
            user.put("image", temp);
            userList.add(user);
        }
        return  userList;
    }
    public ArrayList<HashMap<String, String>> GetUsers(Object e){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
//        String query = "SELECT  * FROM " + TABLE_NAME;
//        Cursor cursor = db.rawQuery(query,null);
        Cursor cursor = db.rawQuery(
                "select * from " + TABLE_NAME + " where EMAIL = ? ", new String[]{String.valueOf(e)});
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            byte[] bb = cursor.getBlob(6);

            String temp=Base64.encodeToString(bb, Base64.DEFAULT);

            user.put("name",cursor.getString(1));
            user.put("price",cursor.getString(3));
            user.put("quantity",cursor.getString(4));
            user.put("size",cursor.getString(5));
            user.put("image", temp);
            userList.add(user);
        }
        return  userList;
    }
}
