package com.vikas.myproject1.Add_Item_Page;

import android.graphics.Bitmap;

public class CustomItemObjectClass {

    // Store the id of the  movie poster
    private Bitmap mImageDrawable;
    // Store the name of the movie
    private String mName;
    // Store the release date of the movie
    private String mPrice;

    // Constructor that is used to create an instance of the Movie object
    public CustomItemObjectClass(Bitmap mImageDrawable, String mName, String mRelease) {
        this.mImageDrawable = mImageDrawable;
        this.mName = mName;
        this.mPrice = mRelease;
    }

    public Bitmap getmImageDrawable() {
        return mImageDrawable;
    }

//    public void setmImageDrawable(Bitmap mImageDrawable) {
//        this.mImageDrawable = mImageDrawable;
//    }

    public String getmName() {
        return mName;
    }

//    public void setmName(String mName) {
//        this.mName = mName;
//    }

    public String getmPrice() {
        return mPrice;
    }

//    public void setmRelease(String mRelease) {
//        this.mPrice = mRelease;
//    }
}