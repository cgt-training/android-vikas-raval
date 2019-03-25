package com.vikas.myproject1.Item_Details;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vikas.myproject1.Add_Item_Page.Add_Item;
import com.vikas.myproject1.Add_Item_Page.Item_Database;
import com.vikas.myproject1.Payment_Order;
import com.vikas.myproject1.R;
import com.vikas.myproject1.UserLoginRegistration.Registration_Database;
import com.vikas.myproject1.User_Navigation.My_Thank_You_Page;

import java.io.ByteArrayOutputStream;

public class Item_Detail extends AppCompatActivity {

    String n,s,q,c,d,p,i_n;
    TextView n_i,s_i,c_i,d_i,p_i,em,abc,qt;
    Button v_i,a_c,a_v;
    EditText in,q_i;
    Item_Detail_Database myDb;
    byte[] imageData;
    private int requestCode;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);
        myDb = new Item_Detail_Database(this);
        getSupportActionBar().setTitle("Item Detail");

        em=(TextView) findViewById(R.id.em);
        n_i=(TextView) findViewById(R.id.n);
        p_i=(TextView) findViewById(R.id.p);
        s_i=(TextView) findViewById(R.id.s);
        q_i=(EditText) findViewById(R.id.q);
        c_i=(TextView) findViewById(R.id.c);
        d_i=(TextView) findViewById(R.id.d);

        abc=(TextView) findViewById(R.id.abc);
        qt=(TextView) findViewById(R.id.qt);


        a_v = (Button) findViewById(R.id.a_v);
        a_c = (Button) findViewById(R.id.a_item);
        v_i = (Button) findViewById(R.id.v_i);
        in = (EditText) findViewById(R.id.in);




        v_i.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Item_Database db = new Item_Database(getApplicationContext());
                        Cursor res = db.getAllItemDetail(in.getText().toString());
//                        Cursor res = db.getAllData();

                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }
//
//
                        StringBuffer buffer5 = new StringBuffer();
                        StringBuffer buffer1 = new StringBuffer();
                        StringBuffer buffer2 = new StringBuffer();
                        StringBuffer buffer3 = new StringBuffer();
                        StringBuffer buffer4 = new StringBuffer();
                        StringBuffer buffer6 = new StringBuffer();

                        while (res.moveToNext()) {

//                            buffer.append("Id :"+res.getString(0)+"\n");
//                            buffer.append("item_Name :"+ res.getString(1)+"\n");
//                            buffer.append("description :"+ res.getString(2)+"\n");
//                            buffer.append("price :"+ res.getString(3)+"\n\n");
//                            buffer.append("quantity :"+ res.getString(4)+"\n\n");
//                            buffer.append("image :"+ res.getBlob(5)+"\n\n");
//                            buffer.append("size :"+ res.getString(6)+"\n\n");
//                            buffer.append("cat_name :"+ res.getString(7)+"\n\n");

                            ImageView myImage = (ImageView) findViewById(R.id.img1);
                            imageData = res.getBlob(5);
                            myImage.setImageBitmap(BitmapFactory.decodeByteArray(imageData, 0, imageData.length));
                            buffer1.append(""+ res.getString(1)+"");
                            buffer2.append(""+ res.getString(3)+"");
                            buffer3.append(""+ res.getString(6)+"");
                            buffer4.append(""+ res.getString(4)+"");
                            buffer5.append(""+res.getString(7)+"");
                            buffer6.append(""+res.getString(2)+"");
                        }
                        //   Show all data
//                        showMessage("Data",buffer.toString());


                        n=buffer1.toString();
                        n_i.setText(n);

                        p=buffer2.toString();
                        p_i.setText(p);

                        s=buffer3.toString();
                        s_i.setText(s);

                        q=buffer4.toString();
                        qt.setText(q);

                        c=buffer5.toString();
                        c_i.setText(c);

                        d=buffer6.toString();
                        d_i.setText(d);


                    }

                }
        );

        a_c.setOnClickListener(
                new View.OnClickListener() {
                    @SuppressLint("WrongConstant")
                    boolean valid = true;
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onClick(View v) {
                        String el = em.getText().toString();
//                        String qy = q_i.getText().toString();
                        int x= Integer.parseInt(qt.getText().toString());
                        int o= Integer.parseInt(q_i.getText().toString());
//                        if (qy.isEmpty()) {
//                            abc.setError("Please Enter QTY");
//                            valid = false;
//                        }
                       if (o>x) {
                            q_i.setError("Please Enter under Total QTY");
                            valid = false;
                        }
                       else if (el.isEmpty()) {
                            em.setError("Please Enter Your email Address");
                            valid = false;
                        }
                        else {
                            em.setError(null);
                            q_i.setError(null);
                            Registration_Database db = new Registration_Database(getApplicationContext());
                            String e = em.getText().toString();
                            String TABLE_NAME = "registration";
                            SQLiteDatabase dba;
                            dba = openOrCreateDatabase("reg.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
                            Cursor cc = dba.rawQuery(
                                    "select * from " + TABLE_NAME + " where email = ? ", new String[]{e});

                            String emll = "";
                            if (cc.getCount() >= 1) {
                                cc.moveToFirst();
                                emll = cc.getString(2);
                            }

                            Log.d("Image In Bytes", "" + imageData);
                            if (e.equals(emll)) {
                                String k=q_i.getText().toString();
                                int f= Integer.parseInt(p);
                                int s= Integer.parseInt(k);
                                int g=f*s;
                                String j= String.valueOf(g);
                                abc.setText(j);
                                String m=q_i.getText().toString();
                                String n=qt.getText().toString();
                                int z= Integer.parseInt(m);
                                int y= Integer.parseInt(n);
                                int l=y-z;
                                String d= String.valueOf(l);
                                qt.setText(d);

                                Item_Database db5 = new Item_Database(getApplicationContext());
                                boolean isUpdate = db5.updateData1(in.getText().toString(), qt.getText().toString());
                                if (isUpdate == true)
                                    Toast.makeText(Item_Detail.this, "Data Update", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(Item_Detail.this, "Data not Updated", Toast.LENGTH_LONG).show();
                                boolean isInserted = myDb.insertData(
                                        n_i.getText().toString(),
                                        d_i.getText().toString(),
                                        abc.getText().toString(), q_i.getText().toString(), s_i.getText().toString(), imageData, c_i.getText().toString(), em.getText().toString());

                                if (isInserted == true) {
                                    Intent intent;
                                    intent = new Intent(Item_Detail.this, My_Thank_You_Page.class);
                                    startActivity(intent);
                                    Toast.makeText(Item_Detail.this, "Data Inserted", Toast.LENGTH_LONG).show();

                                } else
                                    Toast.makeText(Item_Detail.this, "Data not Inserted", Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(Item_Detail.this, "Please Enter your Email", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    }

        );


        a_v.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();

                        while (res.moveToNext()) {


                            buffer.append("Id :" + res.getString(0) + "\n");
                            buffer.append("item_Name :" + res.getString(1) + "\n");
                            buffer.append("description :" + res.getString(2) + "\n");
                            buffer.append("price :" + res.getString(3) + "\n\n");
                            buffer.append("quantity :" + res.getString(4) + "\n\n");
                            buffer.append("size :" + res.getString(5) + "\n\n");
//                            buffer.append("cat_name :" + res.getString(7) + "\n\n");
//                           buffer.append("image :" + res.getString(6) + "\n\n");


                        }


                        //   Show all data
                        showMessage("Data", buffer.toString());

                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}

