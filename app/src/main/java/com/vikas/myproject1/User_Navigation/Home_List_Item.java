package com.vikas.myproject1.User_Navigation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vikas.myproject1.R;

public class Home_List_Item extends AppCompatActivity {

    Button btnv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_list_item);
//        btnv1 = (Button) findViewById(R.id.btnv);
//
//        btnv1.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent;
//                        intent = new Intent(Home_List_Item.this, Item_Detail.class);
//                        startActivity(intent);
//                    }
//                }
//        );
    }
}
