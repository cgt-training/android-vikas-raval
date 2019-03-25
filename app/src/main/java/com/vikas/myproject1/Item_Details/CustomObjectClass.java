package com.vikas.myproject1.Item_Details;


import android.graphics.Bitmap;

public class CustomObjectClass {

    // Store the id of the  movie poster
    private Bitmap mImageDrawable;
    // Store the name of the movie
    private String mName;
    // Store the release date of the movie
    private String mPrice;
    private String mQty;
    private String mSiz;

    // Constructor that is used to create an instance of the Movie object
    public CustomObjectClass(Bitmap mImageDrawable,String mName, String mRelease,String mQty,String mSiz) {
        this.mImageDrawable = mImageDrawable;
        this.mName = mName;
        this.mPrice = mRelease;
        this.mQty = mQty;
        this.mSiz = mSiz;

    }

    public Bitmap getmImageDrawable() {
        return mImageDrawable;
    }



    public String getmName() {
        return mName;
    }



    public String getmPrice() {
        return mPrice;
    }

    public String getmQty() {
        return mQty;
    }

    public String getmSiz() {
        return mSiz;
    }




}
