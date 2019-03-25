package com.vikas.myproject1.User_Navigation;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vikas.myproject1.R;
import com.vikas.myproject1.UserLoginRegistration.Registration_Database;

public class My_Change_Password extends AppCompatActivity {
    EditText new_pass,old_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_chage_password);
        getSupportActionBar().setTitle("My Change Password");

        old_pass=(EditText) findViewById(R.id.old_pass);
        new_pass=(EditText) findViewById(R.id.new_pass);
        Button pbtn=(Button) findViewById(R.id.e_n_p_btn);
        pbtn.setOnClickListener(
                new View.OnClickListener() {
                    boolean valid = true;
                    @Override
                    public void onClick(View v) {
                        String o_pass = old_pass.getText().toString();
                        String n_pass = new_pass.getText().toString();
                        if (o_pass.isEmpty()) {
                            old_pass.setError("Please Enter Your Old Password");
                            valid = false;
                        }
                        else if (n_pass.isEmpty()) {
                            new_pass.setError("Please Enter Your New Password");
                            valid = false;
                        }
                        else {
                            old_pass.setError(null);
                            new_pass.setError(null);
                            Registration_Database db = new Registration_Database(getApplicationContext());
                            boolean isUpdate = db.updateData(old_pass.getText().toString(), new_pass.getText().toString());
                            if (isUpdate == true)
                                Toast.makeText(My_Change_Password.this, "Data Update", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(My_Change_Password.this, "Data not Updated", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
}
