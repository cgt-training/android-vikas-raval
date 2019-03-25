package com.vikas.myproject1.Report_Page;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vikas.myproject1.Add_Item_Page.Item_Database;
import com.vikas.myproject1.Category_Page.Category_Database;
import com.vikas.myproject1.R;

import java.util.List;

public class Reports extends AppCompatActivity {

    String id,name,pri,qty;
    TextView i_id,i_name,i_prc,i_qt;
    Spinner sp1;
    Button view_but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reports);
        getSupportActionBar().setTitle("View Reports");

        sp1 = (Spinner) findViewById(R.id.rep1);
        view_but = (Button) findViewById(R.id.report);
        i_id = (TextView) findViewById(R.id.i_id);
        i_name = (TextView) findViewById(R.id.i_name);
        i_prc = (TextView) findViewById(R.id.i_prc);
        i_qt = (TextView) findViewById(R.id.i_qt);

        loadSpinnerData();
        viewAll();

    }



    private void loadSpinnerData() {
        Category_Database db = new Category_Database(getApplicationContext());

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
                        Cursor res = db.getAllData1(sp1.getItemAtPosition(sp1.getSelectedItemPosition()));
//                        Cursor res = db.getAllData();

                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }
//
//
                        StringBuffer buffer = new StringBuffer();
                        StringBuffer buffer1 = new StringBuffer();
                        StringBuffer buffer2 = new StringBuffer();
                        StringBuffer buffer3 = new StringBuffer();
                        StringBuffer buffer4 = new StringBuffer();

                        while (res.moveToNext()) {

//                            buffer.append("Id :"+res.getString(0)+"\n");
//                            buffer.append("item_Name :"+ res.getString(1)+"\n");
//                            buffer.append("description :"+ res.getString(2)+"\n");
//                            buffer.append("price :"+ res.getString(3)+"\n\n");
//                            buffer.append("quantity :"+ res.getString(4)+"\n\n");
//                            buffer.append("image :"+ res.getBlob(5)+"\n\n");
//                            buffer.append("size :"+ res.getString(6)+"\n\n");
//                            buffer.append("cat_name :"+ res.getString(7)+"\n\n");

                            buffer1.append(""+res.getString(0)+"\n");
                            buffer2.append(""+ res.getString(1)+"\n");
                            buffer3.append(""+ res.getString(3)+"\n");
                            buffer4.append(""+ res.getString(4)+"\n");
                        }
                        //   Show all data
//                        showMessage("Data",buffer.toString());
                        id=buffer1.toString();
                        i_id.setText(id);
                        name=buffer2.toString();
                        i_name.setText(name);
                        pri=buffer3.toString();
                        i_prc.setText(pri);
                        qty=buffer4.toString();
                        i_qt.setText(qty);

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
