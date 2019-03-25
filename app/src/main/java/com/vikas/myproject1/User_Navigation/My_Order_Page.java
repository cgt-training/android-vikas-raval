package com.vikas.myproject1.User_Navigation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.vikas.myproject1.Add_Item_Page.CustomAddItemAdapter;
import com.vikas.myproject1.Item_Details.CustomItemAdapter;
import com.vikas.myproject1.Item_Details.CustomObjectClass;
import com.vikas.myproject1.Item_Details.Item_Detail_Database;
import com.vikas.myproject1.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class My_Order_Page extends My_Account_Page {
    ArrayList<CustomObjectClass> dataModels;
    CustomItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_order_page);
        getSupportActionBar().setTitle("My Oder Page");

//        Item_Detail_Database db = new Item_Detail_Database(getApplicationContext());
//        ArrayList<HashMap<String, String>> userList = db.GetUsers();
//        ListView lv = (ListView) findViewById(R.id.list1);
//        ListAdapter adapter = new SimpleAdapter(My_Order_Page.this, userList, R.layout.my_oder_page_list,new String[]{"item_name","price","quantity"}, new int[]{R.id.total_pric, R.id.iname,R.id.qan});
//        lv.setAdapter(adapter);

        Item_Detail_Database db = new Item_Detail_Database(getApplicationContext());
        ArrayList<HashMap<String, String>> userList = db.GetUsers1(em.getText().toString());
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        dataModels= new ArrayList<CustomObjectClass>();

        for(int i=0;i < userList.size(); i++){

            HashMap<String, String> hm = new HashMap<String, String>();
            android.util.Log.d("UserList",""+ userList.get(i).get("price"));
            String name = userList.get(i).get("name");
            String price = userList.get(i).get("price");
            String qty = userList.get(i).get("quantity");
            String siz = userList.get(i).get("size");
            byte[] decodedString = Base64.decode(userList.get(i).get("image"), Base64.DEFAULT);

            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            dataModels.add(new CustomObjectClass(decodedByte,name, price,qty,siz));

        }

        ListView lv = (ListView) findViewById(R.id.list1);

        adapter= new CustomItemAdapter(dataModels, getApplicationContext());
//        ListAdapter adapter = new SimpleAdapter(My_Home_Page.this, userList, R.layout.home_list_item,new String[]{"name","price","image"}, new int[]{R.id.i_n, R.id.i_p,R.id.iv2});
        lv.setAdapter(adapter);
    }
}
