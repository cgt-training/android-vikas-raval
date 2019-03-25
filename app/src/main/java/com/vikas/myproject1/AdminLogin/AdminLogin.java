package com.vikas.myproject1.AdminLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.vikas.myproject1.Admin_Nevigation.Admin_Nevigation;
import com.vikas.myproject1.R;


public class AdminLogin extends AppCompatActivity {
private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login_page);
        btn=(Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    public void login() {
        Intent intent;
        intent = new Intent(AdminLogin.this, Admin_Nevigation.class);
        startActivity(intent);
    }
}
