package com.vikas.myproject1.Admin_Nevigation;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.vikas.myproject1.AdminLogin.AdminLogin;
import com.vikas.myproject1.Admin_Nevigation.Admin_Nevigation;
import com.vikas.myproject1.Category_Page.Category_Database;
import com.vikas.myproject1.R;
import com.vikas.myproject1.UserLoginRegistration.Log;
import com.vikas.myproject1.UserLoginRegistration.Registration_Database;
import com.vikas.myproject1.User_Navigation.User_Navigation;

import java.util.List;

public class User_Details extends AppCompatActivity {

    Spinner sp1;
    Button view_but;
    TextView nam,em,ph,addr,ct,pn;
    String nem,e,p,add,c,pi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_details);
        getSupportActionBar().setTitle("View User Detaile");
        sp1 = (Spinner) findViewById(R.id.us);
        view_but = (Button) findViewById(R.id.usd);

        nam = (TextView) findViewById(R.id.i);
        em = (TextView) findViewById(R.id.Em);
        ph = (TextView) findViewById(R.id.ph);
        addr = (TextView) findViewById(R.id.addr);
        ct = (TextView) findViewById(R.id.ct);
        pn = (TextView) findViewById(R.id.pn);
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

                        Registration_Database db = new Registration_Database(getApplicationContext());
                        Cursor res = db.getAll(sp1.getItemAtPosition(sp1.getSelectedItemPosition()));
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
                        StringBuffer buffer5 = new StringBuffer();
                        StringBuffer buffer6 = new StringBuffer();

                        while (res.moveToNext()) {
                            buffer1.append("Name:-   "+res.getString(1)+"\n");
                            buffer2.append("Email:-   "+ res.getString(2)+"\n");
                            buffer3.append("Phone No.   "+ res.getString(4)+"\n");
                            buffer4.append("Address:-   "+ res.getString(5)+"\n");
                            buffer5.append("City:-   "+ res.getString(6)+"\n");
                            buffer6.append("Pin Code:-   "+ res.getString(7)+"\n");
                        }
                        //   Show all data
//                        showMessage("Data",buffer.toString());
                        nem=buffer1.toString();
                        nam.setText(nem);
                        e=buffer2.toString();
                        em.setText(e);
                        p=buffer3.toString();
                        ph.setText(p);
                        add=buffer4.toString();
                        addr.setText(add);
                        c=buffer5.toString();
                        ct.setText(c);
                        pi=buffer6.toString();
                        pn.setText(pi);

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
