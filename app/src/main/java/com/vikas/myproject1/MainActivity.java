package com.vikas.myproject1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vikas.myproject1.AdminLogin.AdminLogin;
import com.vikas.myproject1.Admin_Nevigation.Admin_Nevigation;
import com.vikas.myproject1.UserLoginRegistration.Log;
import com.vikas.myproject1.User_Navigation.User_Navigation;

public class MainActivity extends AppCompatActivity {

    Button us,ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        us = (Button)findViewById(R.id.user);
        ad = (Button)findViewById(R.id.admin);

        us.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent;
                        intent = new Intent(MainActivity.this, Log.class);
                        startActivity(intent);
                    }
                }
        );
        ad.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent;
                        intent = new Intent(MainActivity.this, AdminLogin.class);
                        startActivity(intent);
                    }
                }
        );
    }
}
