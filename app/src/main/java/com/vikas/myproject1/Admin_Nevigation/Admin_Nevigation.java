package com.vikas.myproject1.Admin_Nevigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.vikas.myproject1.Add_Item_Page.Add_Item;
import com.vikas.myproject1.AdminLogin.AdminLogin;
import com.vikas.myproject1.Category_Page.Category;
import com.vikas.myproject1.R;
import com.vikas.myproject1.Report_Page.Reports;
import com.vikas.myproject1.View_Order_Page.View_Order;

public class Admin_Nevigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__nevigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin__nevigation, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.category) {
            Intent intent;
            intent = new Intent(Admin_Nevigation.this, Category.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.item123) {
            Intent intent;
            intent = new Intent(Admin_Nevigation.this, Add_Item.class);
            startActivity(intent);

        } else if (id == R.id.quantity) {
            Intent intent;
            intent = new Intent(Admin_Nevigation.this, Quantity.class);
            startActivity(intent);


        } else if (id == R.id.order) {
            Intent intent;
            intent = new Intent(Admin_Nevigation.this, View_Order.class);
            startActivity(intent);

        }

        else if (id == R.id.report) {
            Intent intent;
            intent = new Intent(Admin_Nevigation.this, Reports.class);
            startActivity(intent);

        }
        else if (id == R.id.cha_pass) {
            Intent intent;
            intent = new Intent(Admin_Nevigation.this, Change_Password.class);
            startActivity(intent);

        }
        else if (id == R.id.us1) {
            Intent intent;
            intent = new Intent(Admin_Nevigation.this, User_Details.class);
            startActivity(intent);

        }
        else if (id == R.id.logout) {
            Intent intent;
            intent = new Intent(Admin_Nevigation.this, AdminLogin.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
