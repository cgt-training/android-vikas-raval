package com.vikas.myproject1.Category_Page;

//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import com.vikas.myproject1.R;
//
//public class Category extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.category);
//        getSupportActionBar().setTitle("Category");
//    }



import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;

import android.os.Bundle;
import android.support.v7.widget.ActionBarContextView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;

import com.vikas.myproject1.R;
import com.vikas.myproject1.UserLoginRegistration.Log;
import com.vikas.myproject1.UserLoginRegistration.Registration;

/**
 * Created by vikas
 */

public class Category extends AppCompatActivity {
    Category_Database myDb;
    EditText addcat1,de_id;
    TextView tx_id,tx_cat;
    String cat_name,cat_id;
    Button adcat,view_cat,del_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);
        getSupportActionBar().setTitle("Category");
        myDb = new Category_Database(this);

        tx_id = (TextView) findViewById(R.id.tx_id);
        tx_cat = (TextView) findViewById(R.id.tx_cat);
        addcat1 = (EditText) findViewById(R.id.addcat1);
        de_id = (EditText)findViewById(R.id.de_id);
        adcat = (Button) findViewById(R.id.adcat);
        view_cat = (Button) findViewById(R.id.view_cat);
        del_id = (Button)findViewById(R.id.del_id);
        AddData();
        viewAll();
        DeleteData();
    }


        public void DeleteData() {
        del_id.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(de_id.getText().toString());
//                        Intent intent;
//                        intent = new Intent(Category.this, Category.class);
//                        startActivity(intent);
                        if(deletedRows > 0)
                            Toast.makeText(Category.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Category.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public  void AddData() {
        adcat.setOnClickListener(
                new View.OnClickListener() {
                    boolean valid = true;
                    @Override
                    public void onClick(View v) {
                        String c_name = addcat1.getText().toString();
                        if (c_name.isEmpty()) {
                            addcat1.setError("Not empty");
                            valid = false;

                        } else {
                            addcat1.setError(null);
                            boolean isInserted = myDb.insertData(addcat1.getText().toString());
                            if (isInserted == true)
                                Toast.makeText(Category.this, "Data Inserted", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(Category.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void viewAll() {
        view_cat.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer1 = new StringBuffer();
                        StringBuffer buffer2 = new StringBuffer();


                        while (res.moveToNext()) {
                            buffer1.append(""+res.getString(0)+"\n");
                            buffer2.append(""+ res.getString(1)+"\n");

                        }


                        //   Show all data
//                        showMessage("Data",buffer.toString());
                        cat_id=buffer1.toString();
                        tx_id.setText(cat_id);
                        cat_name=buffer2.toString();
                        tx_cat.setText(cat_name);

                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

