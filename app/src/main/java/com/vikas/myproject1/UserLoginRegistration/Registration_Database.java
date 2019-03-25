package com.vikas.myproject1.UserLoginRegistration;


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
public class Registration_Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "reg.db";
    public static final String TABLE_NAME = "registration";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "EMAIL";
    public static final String COL_4 = "PASSWORD";
    public static final String COL_5 = "PHONE_NO";
    public static final String COL_6 = "ADDRESS";
    public static final String COL_7 = "CITY";
    public static final String COL_8 = "PIN_CODE";
    public static final String COL_9 = "IMAGE";


    public Registration_Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,EMAIL TEXT,PASSWORD INTEGER,PHONE_NO INTEGER,ADDRESS TEXT,CITY TEXT,PIN_CODE INTEGER,IMAGE BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String email, String password, String phone_no, String address,String city,String pin_code,byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, email);
        contentValues.put(COL_4, password);
        contentValues.put(COL_5, phone_no);
        contentValues.put(COL_6, address);
        contentValues.put(COL_7, city);
        contentValues.put(COL_8, pin_code);
        contentValues.put(COL_9, image);
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

//    public Cursor update(Object CATEGORY_NAME) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where CATEGORY_NAME =?", new String[]{String.valueOf(CATEGORY_NAME)});
//
//        return res;
//    }
//
//    public Cursor update(String PASSWORD) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where PASSWORD =?", new String[]{PASSWORD});
//
//        return res;
//    }

    public boolean updateData(String pass1,String pass2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_1,pass1);
        contentValues.put(COL_4,pass2);
        db.update(TABLE_NAME, contentValues, "PASSWORD = ?",new String[] { pass1 });
        return true;
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
                labels.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }


    public Cursor getAll(Object EMAIL) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where EMAIL =?", new String[]{String.valueOf(EMAIL)});

        return res;
    }
}
