package com.vikas.myproject1.View_Order_Page;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.vikas.myproject1.Add_Item_Page.Item_Database;
import com.vikas.myproject1.Category_Page.Category_Database;
import com.vikas.myproject1.Item_Details.CustomItemAdapter;
import com.vikas.myproject1.Item_Details.CustomObjectClass;
import com.vikas.myproject1.Item_Details.Item_Detail_Database;
import com.vikas.myproject1.R;
import com.vikas.myproject1.UserLoginRegistration.Registration_Database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class View_Order extends AppCompatActivity {
    ArrayList<CustomClass> dataModels;
    CustomAdapter adapter;
    Spinner sp1;
    Button view_but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_order);
        getSupportActionBar().setTitle("View Order");

        sp1 = (Spinner) findViewById(R.id.s_u1);
        view_but = (Button) findViewById(R.id.order_status);
        loadSpinnerData();
        viewAll();
    }
    private void loadSpinnerData() {
       Registration_Database db = new Registration_Database(getApplicationContext());

        // Spinner Drop down elements
        List<String> lables = db.getAllLabels();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        sp1.setAdapter(dataAdapter);
    }

    private void viewAll()
    {
        view_but.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                Item_Detail_Database db = new Item_Detail_Database(getApplicationContext());
                        Cursor res = db.getAllItemDetail2(sp1.getItemAtPosition(sp1.getSelectedItemPosition()));
                        ListView lv = (ListView) findViewById(R.id.ol);
                        lv.setVisibility(View.VISIBLE);
                        if(res.getCount() == 0) {
                            // show message
                            lv.setVisibility(View.GONE);
                            showMessage("Error","Nothing found");
                            return;
                        }
                        ArrayList<HashMap<String, String>> userList = db.GetUsers(sp1.getItemAtPosition(sp1.getSelectedItemPosition()));
                        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

                        dataModels= new ArrayList<CustomClass>();

                        for(int i=0;i < userList.size(); i++){

                            HashMap<String, String> hm = new HashMap<String, String>();
                            android.util.Log.d("UserList",""+ userList.get(i).get("price"));
                            String name = userList.get(i).get("name");
                            String price = userList.get(i).get("price");
                            String qty = userList.get(i).get("quantity");
                            String siz = userList.get(i).get("size");
                            byte[] decodedString = Base64.decode(userList.get(i).get("image"), Base64.DEFAULT);

                            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                            dataModels.add(new CustomClass(decodedByte,name, price,qty,siz));

                        }



                        adapter= new CustomAdapter(dataModels, getApplicationContext());
//        ListAdapter adapter = new SimpleAdapter(My_Home_Page.this, userList, R.layout.home_list_item,new String[]{"name","price","image"}, new int[]{R.id.i_n, R.id.i_p,R.id.iv2});
                        lv.setAdapter(adapter);
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
