package com.vikas.myproject1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.vikas.myproject1.Item_Details.Item_Detail_Database;
import com.vikas.myproject1.User_Navigation.My_Home_Page;


public class Payment_Order extends AppCompatActivity {

    Payment_Order_Database myDb;
    Button pay,view;
    EditText e_p,e_pay,cardno,cvv;
    Spinner spinner1,spinner2;
    String p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDb = new Payment_Order_Database(this);
        setContentView(R.layout.payment_order);

        pay = (Button) findViewById(R.id.button1);
        view = (Button) findViewById(R.id.button2);
        e_p = (EditText) findViewById(R.id.e_p);
        e_pay = (EditText) findViewById(R.id.e_pay);
        cardno = (EditText) findViewById(R.id.cardno);
        cvv = (EditText) findViewById(R.id.cvv);
        spinner1 = (Spinner) findViewById(R.id.spin1);
        spinner2 = (Spinner) findViewById(R.id.spin2);

        Item_Detail_Database db = new Item_Detail_Database(getApplicationContext());
        Cursor res = db.getAllData();
        {
            StringBuffer buffer = new StringBuffer();

            while (res.moveToNext()) {


                buffer.append("" + res.getString(3) + "");

            }


            //   Show all data
//            showMessage("Data", buffer.toString());
            p=buffer.toString();
            e_p.setText(p);

        }

        pay.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(
                                e_p.getText().toString(),
                                e_pay.getText().toString(),
                                 spinner1.getItemAtPosition(spinner1.getSelectedItemPosition()), spinner2.getItemAtPosition(spinner2.getSelectedItemPosition()),cardno.getText().toString(), cvv.getText().toString());
                        if (isInserted == true)
                            Toast.makeText(Payment_Order.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Payment_Order.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );


        view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent;
                        intent = new Intent(Payment_Order.this, My_Home_Page.class);
                        startActivity(intent);
//                        Cursor res = myDb.getAllData();
//                        if (res.getCount() == 0) {
//                            // show message
//                            showMessage("Error", "Nothing found");
//                            return;
//                        }
//
//                        StringBuffer buffer = new StringBuffer();
//
//                        while (res.moveToNext()) {
//
//
//                            buffer.append("Id :" + res.getString(0) + "\n");
//                            buffer.append("total price :" + res.getString(1) + "\n");
//                            buffer.append("payment type :" + res.getString(2) + "\n");
//                            buffer.append("bank name :" + res.getString(3) + "\n\n");
//                            buffer.append("branch:" + res.getString(4) + "\n\n");
//                            buffer.append("card no :" + res.getString(5) + "\n\n");
//                            buffer.append("cvv no :" + res.getString(6) + "\n\n");
//
//
//                        }
//
//
//                        //   Show all data
//                        showMessage("Data", buffer.toString());
//
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