package com.vikas.myproject1.UserLoginRegistration;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vikas.myproject1.Add_Item_Page.Add_Item;
import com.vikas.myproject1.Add_Item_Page.Item_Database;
import com.vikas.myproject1.R;

import java.io.ByteArrayOutputStream;

public class Registration extends AppCompatActivity {

    Uri selectedImageUri,selectedImageUri1;
    Integer REQUEST_CAMERA = 1, SELECT_FILE = 0, CAMERA = 22;
    private int requestCode;
    byte[] imageData;

    Registration_Database myDb;
private TextView log;
ImageView ivImage1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        myDb = new Registration_Database(this);
        Button add = (Button) findViewById(R.id.btnReg);
       Button view_item = (Button) findViewById(R.id.view);
        final EditText name = (EditText) findViewById(R.id.txtName);
       final EditText email = (EditText) findViewById(R.id.txtEmail);
        final EditText pass = (EditText) findViewById(R.id.pass1);
        final EditText phone = (EditText) findViewById(R.id.phone1);
        final EditText add1 = (EditText) findViewById(R.id.add1);
        final EditText city = (EditText) findViewById(R.id.city1);
        final EditText pin = (EditText) findViewById(R.id.pin1);

        log=(TextView) findViewById(R.id.log);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        ivImage1 = (ImageView) findViewById(R.id.ivImage);
        ivImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });

        add.setOnClickListener(
                new View.OnClickListener() {
                    boolean valid = true;
                    @Override
                    public void onClick(View v) {
                        String na = name.getText().toString();
                        String el = email.getText().toString();
                        String ps = pass.getText().toString();
                        String ph = phone.getText().toString();
                        String ad = add1.getText().toString();
                        String ct = city.getText().toString();
                        String pn = pin.getText().toString();
                        if (na.isEmpty()) {
                            name.setError("Please Enter Your Name");
                            valid = false;
                        }
                        else if (el.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(el).matches()) {
                            email.setError("Enter Your Valid Email Address");
                            valid = false;
                        }
                       else if (ps.isEmpty() || ps.length() < 4 || ps.length() > 10) {
                            pass.setError("between 4 and 10 alphanumeric characters");
                            valid = false;
                        }
                      else if (ph.isEmpty() || ph.length() < 10) {
                            phone.setError("enter a valid phone number only 10 characters");
                            valid = false;
                        }
                        else if (ad.isEmpty()) {
                            add1.setError("Please Enter Your Address");
                            valid = false;
                        }
                        else if (ct.isEmpty()) {
                            city.setError("Please Enter Your city");
                            valid = false;
                        }
                        else if (pn.isEmpty()) {
                            pin.setError("Please Enter Your city");
                            valid = false;
                        }
                        else {
                            name.setError(null);
                            email.setError(null);
                            pass.setError(null);
                            phone.setError(null);
                            add1.setError(null);
                            city.setError(null);
                            pin.setError(null);
                            boolean isInserted = myDb.insertData(
                                    name.getText().toString(),
                                    email.getText().toString(),
                                    pass.getText().toString(), phone.getText().toString(), add1.getText().toString(), city.getText().toString(), pin.getText().toString(), imageData);
                            if (isInserted == true)
                                Toast.makeText(Registration.this, "Data Inserted", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(Registration.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );

        view_item.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }


                        StringBuffer buffer = new StringBuffer();


                        while (res.moveToNext()) {


                            buffer.append("Id :"+res.getString(0)+"\n");
                            buffer.append("Name :"+ res.getString(1)+"\n");
                            buffer.append("Email :"+ res.getString(2)+"\n");
                            buffer.append("password :"+ res.getString(3)+"\n\n");
                            buffer.append("Phone No :"+ res.getString(4)+"\n\n");
                            buffer.append("Address :"+ res.getString(5)+"\n\n");
                            buffer.append("City :"+ res.getString(6)+"\n\n");
                            buffer.append("Pin Code :"+ res.getString(7)+"\n\n");
                            buffer.append(" Image:"+ res.getBlob(8)+"\n\n");


                        }

                        //   Show all data
                        showMessage("Data",buffer.toString());

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
    public void login() {
        Intent intent;
        intent = new Intent(Registration.this, Log.class);
        startActivity(intent);
    }

    public void SelectImage() {
//        Toast.makeText(Pic.this, "Select Image", Toast.LENGTH_SHORT).show();
        final CharSequence[] items = {"Camera", "Gallery", "Cancel"};

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(Registration.this);
        builder.setTitle("Add Image");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (items[i].equals("Camera")) {

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(intent, REQUEST_CAMERA);
                    }

                } else if (items[i].equals("Gallery")) {

                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent, "Select File"), SELECT_FILE);

                } else if (items[i].equals("Cancel")) {
                    dialog.dismiss();
//                    Intent intent;
//                    intent = new Intent(Add_Item.this, Add_Item.class);
//                    startActivity(intent);
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.requestCode = requestCode;
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == REQUEST_CAMERA) {
                Bundle bundle = data.getExtras();
                final Bitmap bmp = (Bitmap) bundle.get("data");

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                imageData = stream.toByteArray();
                ivImage1.setImageBitmap(bmp);
                CAMERA = 0;


                // Handling Bitmap file
//                persistImage(this, bmp, "BitmapImage");
//                Toast.makeText(Registration.this,"Hellow form iMage",Toast.LENGTH_LONG).show();

            }

            else if (requestCode == SELECT_FILE) {
                CAMERA = 1;
                selectedImageUri = data.getData();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                imageData = stream.toByteArray();
                ivImage1.setImageURI(selectedImageUri);

            }
        }


    }
}
