package com.vikas.myproject1.Admin_Nevigation;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.vikas.myproject1.Add_Item_Page.Item_Database;
import com.vikas.myproject1.Item_Details.Item_Detail_Database;
import com.vikas.myproject1.R;

import java.util.List;

public class Quantity extends AppCompatActivity {

    Button view_but;
    Spinner sp1;
    TextView name,total_qty,sel_qty;
    String nam,t_qty,s_qty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_quantity);
        getSupportActionBar().setTitle("Add Quantity");
        view_but = (Button) findViewById(R.id.ad_qty);
        sp1 = (Spinner) findViewById(R.id.s_i1);
        name = (TextView) findViewById(R.id.textname);
        total_qty = (TextView) findViewById(R.id.total_qty);
        sel_qty = (TextView) findViewById(R.id.sel_qt);

        loadSpinnerData();
        viewAll();
    }
    private void loadSpinnerData() {
        Item_Database db = new Item_Database(getApplicationContext());

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

                        Item_Database db = new Item_Database(getApplicationContext());
                        Cursor res = db.getAllItemDetail(sp1.getItemAtPosition(sp1.getSelectedItemPosition()));
//                        Cursor res = db.getAllData();

                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }
//
//

                        StringBuffer buffer1 = new StringBuffer();
                        StringBuffer buffer2 = new StringBuffer();


                        while (res.moveToNext()) {

//                            buffer.append("Id :"+res.getString(0)+"\n");
                            buffer1.append(""+ res.getString(1)+"\n");
//                            buffer.append("description :"+ res.getString(2)+"\n");
//                            buffer.append("price :"+ res.getString(3)+"\n\n");
                            buffer2.append(""+ res.getString(4)+"\n");
//                            buffer.append("image :"+ res.getBlob(5)+"\n\n");
//                            buffer.append("size :"+ res.getString(6)+"\n\n");
//                            buffer.append("cat_name :"+ res.getString(7)+"\n\n");
                        }

                        Item_Detail_Database db1 = new Item_Detail_Database(getApplicationContext());
                        Cursor res1 = db1.getAll(sp1.getItemAtPosition(sp1.getSelectedItemPosition()));
                        StringBuffer buffer3= new StringBuffer();
                        while (res1.moveToNext()) {

                            buffer3.append("" + res1.getString(4) + "\n");

                        }
                        //   Show all data
//                        showMessage("Data",buffer.toString());
                        nam=buffer1.toString();
                        name.setText(nam);
                        t_qty=buffer2.toString();
                        total_qty.setText(t_qty);
                        s_qty=buffer3.toString();
                        sel_qty.setText(s_qty);

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