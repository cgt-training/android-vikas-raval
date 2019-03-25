package com.vikas.myproject1.UserLoginRegistration;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vikas.myproject1.R;
import com.vikas.myproject1.User_Navigation.User_Navigation;

import java.util.ArrayList;
import java.util.HashMap;

public class Log extends AppCompatActivity {
private Button sign,lon;
    SessionManager session;
    String name,email,password,phone,address,city,pin;
    EditText em;
    EditText pw;
    String e,p;
    boolean valid = true;
@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.login);
    session = new SessionManager(getApplicationContext());

    em=(EditText) findViewById(R.id.em);
    pw=(EditText) findViewById(R.id.pw);

    sign=(Button) findViewById(R.id.sign);
    sign.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            reg();
        }
    });
    lon=(Button) findViewById(R.id.lon);
    lon.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            LoginCheck();
        }
    });
}

    public void reg() {
        Intent intent;
        intent = new Intent(Log.this, Registration.class);
        startActivity(intent);
    }

@SuppressLint("WrongConstant")
private void LoginCheck () {
    String el = em.getText().toString();
    String ps = pw.getText().toString();
    if (el.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(el).matches()) {
        em.setError("Enter Your Valid Email Address");
        valid = false;
    }
    if (ps.isEmpty()) {
        pw.setError("Enter Your Valid Password");
        valid = false;
    }
    else {
        em.setError(null);
        pw.setError(null);
        Registration_Database db = new Registration_Database(getApplicationContext());
        e = em.getText().toString();
        p = pw.getText().toString();
        String TABLE_NAME = "registration";
        SQLiteDatabase dba;
        dba = openOrCreateDatabase("reg.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        Cursor cc = dba.rawQuery(
                "select * from " + TABLE_NAME + " where email = ? ", new String[]{e});

        String emll = "";
        String pasl = "";

        StringBuffer buffer = new StringBuffer();
        StringBuffer buffer2 = new StringBuffer();
        StringBuffer buffer3 = new StringBuffer();
        StringBuffer buffer4 = new StringBuffer();
        StringBuffer buffer5 = new StringBuffer();
        StringBuffer buffer6 = new StringBuffer();
        StringBuffer buffer7 = new StringBuffer();

        if (cc.getCount() >= 1) {
            cc.moveToFirst();
            emll = cc.getString(2);
            pasl = cc.getString(3);
            buffer.append("" + cc.getString(1) + "");
            buffer2.append("" + cc.getString(2) + "");
            buffer3.append("" + cc.getString(3) + "");
            buffer4.append("" + cc.getString(4) + "");
            buffer5.append("" + cc.getString(5) + "");
            buffer6.append("" + cc.getString(6) + "");
            buffer7.append("" + cc.getString(7) + "");
        }

        name = buffer.toString();
        email = buffer2.toString();
        password = buffer3.toString();
        phone = buffer4.toString();
        address = buffer5.toString();
        city = buffer6.toString();
        pin = buffer7.toString();

        try {
            if (e.equals(emll) && p.equals(pasl)) {
//                            Toast.makeText(Log.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                Intent intent;
                intent = new Intent(Log.this, User_Navigation.class);
                session.createLoginSession("" + name, "" + email, "" + password, "" + phone, "" + address, "" + city, "" + pin);
                startActivity(intent);
            } else {
                Toast.makeText(Log.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            android.util.Log.d("Exception", "" + e);
        }
    }
}

}


