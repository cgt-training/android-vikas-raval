package com.vikas.myproject1.Add_Item_Page;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vikas.myproject1.Category_Page.Category_Database;
import com.vikas.myproject1.R;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class Add_Item extends AppCompatActivity {


    Uri selectedImageUri,selectedImageUri1;
    Integer REQUEST_CAMERA = 1, SELECT_FILE = 0, CAMERA = 22;
    ImageView ivImage1;
    ImageView ivImage2;
    ImageView ivImage3;

    String id,item,pric;
    TextView t_id,t_item,t_price;
    private int requestCode;
    Item_Database myDb;
    Button additem,view_item,de_but;
    EditText i_name,des,price,qty,d_t;
    Spinner sp1,sp2;
    byte[] imageData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);
        myDb = new Item_Database(this);

        getSupportActionBar().setTitle("Add Item");
        t_id = (TextView) findViewById(R.id.t_id);
        t_item = (TextView) findViewById(R.id.t_item);
        t_price = (TextView) findViewById(R.id.t_price);

        additem = (Button) findViewById(R.id.add_item);
        view_item = (Button) findViewById(R.id.view_item);
        i_name = (EditText) findViewById(R.id.I_N1);
        des = (EditText) findViewById(R.id.des1);
        price = (EditText) findViewById(R.id.price1);
        qty = (EditText) findViewById(R.id.qty1);
        sp1 = (Spinner) findViewById(R.id.size1);
        sp2 = (Spinner) findViewById(R.id.cat_nem1);

        //for imageview1
        ivImage1 = (ImageView) findViewById(R.id.ivImage1);
        ivImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });
        viewAll();
        loadSpinnerData();
//        DeleteData();
//        //for imageview2
//        ivImage2 = (ImageView) findViewById(R.id.ivImage2);
//        ivImage2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SelectImage();
//            }
//        });
//
//        //for imageview3
//        ivImage3 = (ImageView) findViewById(R.id.ivImage3);
//        ivImage3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SelectImage();
//            }
//        });

        additem.setOnClickListener(
                new View.OnClickListener() {
                    boolean valid = true;
                    @Override
                    public void onClick(View v) {
                        String nem = i_name.getText().toString();
                        String de = des.getText().toString();
                        String pr = price.getText().toString();
                        String qt = qty.getText().toString();
                        if (nem.isEmpty()) {
                            i_name.setError("Please Enter item Name");
                            valid = false;
                        }
                       else if (de.isEmpty()) {
                            des.setError("Please Enter Description");
                            valid = false;
                        }
                        else if(pr.isEmpty())
                        {
                            price.setError("Please Enter price");
                            valid = false;
                        }
                        else if(qt.isEmpty())
                        {
                            qty.setError("Please Enter Quantity");
                            valid = false;
                        }
                        else {
                            i_name.setError(null);
                            des.setError(null);
                            price.setError(null);
                            qty.setError(null);
                            boolean isInserted = myDb.insertData(
                                    i_name.getText().toString(),
                                    des.getText().toString(),
                                    price.getText().toString(), qty.getText().toString(), imageData, sp1.getItemAtPosition(sp1.getSelectedItemPosition()), sp2.getItemAtPosition(sp2.getSelectedItemPosition()));
                            if (isInserted == true)
                                Toast.makeText(Add_Item.this, "Data Inserted", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(Add_Item.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }


//    public void DeleteData() {
//        de_but.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Integer deletedRows = myDb.deleteData(d_t.getText().toString());
////                        Intent intent;
////                        intent = new Intent(Category.this, Category.class);
////                        startActivity(intent);
//                        if(deletedRows > 0)
//                            Toast.makeText(Add_Item.this,"Data Deleted",Toast.LENGTH_LONG).show();
//                        else
//                            Toast.makeText(Add_Item.this,"Data not Deleted",Toast.LENGTH_LONG).show();
//                    }
//                }
//        );
//    }

    private void loadSpinnerData() {
        Category_Database db = new Category_Database(getApplicationContext());

        // Spinner Drop down elements
        List<String> lables = db.getAllLabels();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        sp2.setAdapter(dataAdapter);
    }

    public void viewAll() {
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
//
//
                        StringBuffer buffer = new StringBuffer();
                        StringBuffer buffer1 = new StringBuffer();
                        StringBuffer buffer2 = new StringBuffer();
                        StringBuffer buffer3 = new StringBuffer();
//
//                        buffer.append("ID\t\t\t\t\tName\t\t\t\t\tSurName\t\t\t\t\tMarks\n\n");

                        while (res.moveToNext()) {
//                            buffer1.append(""+res.getString(0)+"\n");
//                            buffer2.append(""+ res.getString(1)+"\n");
//                            buffer3.append(""+ res.getString(3)+"\n");
                        }
//
//                                name = res.getString(1);
//                                txt.setText(name);







//                            ImageView myImage = (ImageView) findViewById(R.id.t_image);
//                            byte[] bb = res.getBlob(5);
//                            myImage.setImageBitmap(BitmapFactory.decodeByteArray(bb, 0, bb.length));

//                            buffer.append(""+res.getString(0)+"\t\t\t\t\t\t");
//                            buffer.append(""+ res.getString(1)+"\t\t\t\t\t\t\t\t");
//                            buffer.append(""+ res.getString(2)+"\t\t\t\t\t\t\t\t");
//                            buffer.append(""+ res.getString(3)+"\n\n");





                        //   Show all data
                        showMessage("Data",buffer.toString());

                        /*id=buffer1.toString();
                        t_id.setText(id);

                        item=buffer2.toString();
                        t_item.setText(item);

                        pric=buffer3.toString();
                        t_price.setText(pric);*/
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


    public void SelectImage() {
//        Toast.makeText(Pic.this, "Select Image", Toast.LENGTH_SHORT).show();
        final CharSequence[] items = {"Camera", "Gallery", "Cancel"};

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(Add_Item.this);
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


//                 Handling Bitmap file
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

