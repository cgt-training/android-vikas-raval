package com.vikas.myproject1.User_Navigation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.vikas.myproject1.Payment_Order_Database;
import com.vikas.myproject1.R;

import java.util.ArrayList;
import java.util.HashMap;

public class My_Payment_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_payment_page);
        getSupportActionBar().setTitle("My Payment Page");

        Payment_Order_Database db = new Payment_Order_Database(getApplicationContext());
        ArrayList<HashMap<String, String>> userList = db.GetUsers();
        ListView lv = (ListView) findViewById(R.id.list);
        ListAdapter adapter = new SimpleAdapter(My_Payment_Page.this, userList, R.layout.my_payment_page_list,new String[]{"total_price","payment","bankname","branch","cardno","cvv"}, new int[]{R.id.total_pric, R.id.payment_type,R.id.bank_name,R.id.branch,R.id.card_no,R.id.cvv_no});
        lv.setAdapter(adapter);
    }
}
