package com.vikas.myproject1.User_Navigation;

import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.vikas.myproject1.R;
import com.vikas.myproject1.UserLoginRegistration.Registration_Database;
import com.vikas.myproject1.UserLoginRegistration.SessionManager;

import java.util.HashMap;

public class My_Account_Page extends AppCompatActivity {

    String na, el, pd, p_n, ct, p_c, a;
    TextView nam, emai, pwd, p_no, cit, p_code, ad,em;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_account_page);
        getSupportActionBar().setTitle("My Account Page");
        session = new SessionManager(getApplicationContext());

//        Button logout = (Button) findViewById(R.id.button);
        nam = (TextView) findViewById(R.id.name);
        emai = (TextView) findViewById(R.id.emai);
        pwd = (TextView) findViewById(R.id.pwd);
        p_no = (TextView) findViewById(R.id.p_no);
        cit = (TextView) findViewById(R.id.cit);
        p_code = (TextView) findViewById(R.id.p_code);
        ad = (TextView) findViewById(R.id.ad);
        em = (TextView) findViewById(R.id.em);

        HashMap<String, String> user = session.getUserDetails();

        // name
        String name = user.get(SessionManager.KEY_NAME);
        String email = user.get(SessionManager.KEY_EMAIL);
        String password = user.get(SessionManager.KEY_PASS);
        String phone = user.get(SessionManager.KEY_PHONE);
        String address = user.get(SessionManager.KEY_ADDRESS);
        String city = user.get(SessionManager.KEY_CITY);
        String pin = user.get(SessionManager.KEY_PIN);


        em.setText(email);

        nam.setText("Name: " + name + "\n");
        emai.setText("Email: " + email + "\n");
        pwd.setText("Password:" + password + "\n");
        p_no.setText("Phone: " + phone + "\n");
        ad.setText("Address: " + address + "\n");
        cit.setText("City: " + city + "\n");
        p_code.setText("Pin Code: " + pin + "\n");

//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //reg();
//                session.logoutUser();
//            }
//        });
    }
}

       /* Registration_Database db = new Registration_Database(getApplicationContext());
        Cursor res = db.getAllData();
//                        Cursor res = db.getAllData();

        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found");
            return;
        }
//
//
        StringBuffer buffer5 = new StringBuffer();
        StringBuffer buffer1 = new StringBuffer();
        StringBuffer buffer2 = new StringBuffer();
        StringBuffer buffer3 = new StringBuffer();
        StringBuffer buffer4 = new StringBuffer();
        StringBuffer buffer6 = new StringBuffer();
        StringBuffer buffer7 = new StringBuffer();

        while (res.moveToNext()) {

//                            buffer.append("Id :"+res.getString(0)+"\n");
//                            buffer.append("item_Name :"+ res.getString(1)+"\n");
//                            buffer.append("description :"+ res.getString(2)+"\n");
//                            buffer.append("price :"+ res.getString(3)+"\n\n");
//                            buffer.append("quantity :"+ res.getString(4)+"\n\n");
//                            buffer.append("image :"+ res.getBlob(5)+"\n\n");
//                            buffer.append("size :"+ res.getString(6)+"\n\n");
//                            buffer.append("cat_name :"+ res.getString(7)+"\n\n");

            ImageView myImage = (ImageView) findViewById(R.id.i1);
            byte[] bb = res.getBlob(8);
            myImage.setImageBitmap(BitmapFactory.decodeByteArray(bb, 0, bb.length));
            buffer1.append(""+ res.getString(1)+"");
            buffer2.append(""+ res.getString(2)+"");
            buffer3.append(""+ res.getString(3)+"");
            buffer4.append(""+ res.getString(4)+"");
            buffer5.append(""+res.getString(5)+"");
            buffer6.append(""+res.getString(6)+"");
            buffer7.append(""+res.getString(7)+"");
        }
        //   Show all data
//                        showMessage("Data",buffer.toString());

        na=buffer1.toString();
        nam.setText(na);

        el=buffer2.toString();
        emai.setText(el);

        pd=buffer3.toString();
        pwd.setText(pd);

        p_n=buffer4.toString();
        p_no.setText(p_n);

        a=buffer5.toString();
        ad.setText(a);

        ct=buffer6.toString();
        cit.setText(ct);

        p_c=buffer7.toString();
        p_code.setText(p_c);*/





//    public void showMessage(String title,String Message){
//        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
//        builder.setCancelable(true);
//        builder.setTitle(title);
//        builder.setMessage(Message);
//        builder.show();
//    }


