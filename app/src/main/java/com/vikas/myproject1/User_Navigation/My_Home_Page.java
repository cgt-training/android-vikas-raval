package com.vikas.myproject1.User_Navigation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.vikas.myproject1.Add_Item_Page.CustomAddItemAdapter;
import com.vikas.myproject1.Add_Item_Page.CustomItemObjectClass;
import com.vikas.myproject1.Add_Item_Page.Item_Database;
import com.vikas.myproject1.Category_Page.Category_Database;
import com.vikas.myproject1.R;
import com.vikas.myproject1.UserLoginRegistration.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class My_Home_Page extends AppCompatActivity {


    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_home_page);

        getSupportActionBar().setTitle("Home Page");



        Item_Database db = new Item_Database(getApplicationContext());
        ArrayList<HashMap<String, String>> userList = db.GetUsers();
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
        for(int i=0;i < userList.size(); i++){

            HashMap<String, String> hm = new HashMap<String, String>();
            android.util.Log.d("UserList",""+ userList.get(i).get("price"));
            byte[] decodedString = Base64.decode(userList.get(i).get("image"), Base64.DEFAULT);

            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

//                hm.put("listview_title", listviewTitle[i]);
//                hm.put("listview_discription", listviewShortDescription[i]);
//                hm.put("listview_image", Integer.toString(listviewImage[i]));
//                hm.put("listview_image1", Integer.toString(listviewImage1[i]));
//                aList.add(hm);

        }
        ListView lv = (ListView) findViewById(R.id.li1);
        ListAdapter adapter = new SimpleAdapter(My_Home_Page.this, userList, R.layout.home_list_item,new String[]{"name","price","image"}, new int[]{R.id.i_n, R.id.i_p,R.id.iv2});
        lv.setAdapter(adapter);

    }*/

    ArrayList<CustomItemObjectClass> dataModels;
    CustomAddItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_home_page);

        getSupportActionBar().setTitle("Home Page");



        Item_Database db = new Item_Database(getApplicationContext());
        ArrayList<HashMap<String, String>> userList = db.GetUsers();
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        dataModels= new ArrayList<CustomItemObjectClass>();

        for(int i=0;i < userList.size(); i++){

            HashMap<String, String> hm = new HashMap<String, String>();
            android.util.Log.d("UserList",""+ userList.get(i).get("price"));
            String name = userList.get(i).get("name");
            String price = userList.get(i).get("price");
            byte[] decodedString = Base64.decode(userList.get(i).get("image"), Base64.DEFAULT);

            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            dataModels.add(new CustomItemObjectClass(decodedByte, name, price));

        }

        ListView lv = (ListView) findViewById(R.id.li1);

        adapter= new CustomAddItemAdapter(dataModels, getApplicationContext());
//        ListAdapter adapter = new SimpleAdapter(My_Home_Page.this, userList, R.layout.home_list_item,new String[]{"name","price","image"}, new int[]{R.id.i_n, R.id.i_p,R.id.iv2});
        lv.setAdapter(adapter);

    }

}








