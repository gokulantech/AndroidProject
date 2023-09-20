package com.example.productcreation;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updateActivity extends AppCompatActivity {
   EditText product_input,product_name,description;
   Button update_button,delete_button;
   String id,name,desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        product_input = findViewById(R.id.product_id_1);
        product_name = findViewById(R.id.product_name1);
        description = findViewById(R.id.description1);
        update_button = findViewById(R.id.updateButton);
        delete_button = findViewById(R.id.deleteButton);
        getIntentData();
//        update_button.setBackgroundColor(Color.BLUE);
        delete_button.setBackgroundColor(Color.RED);
        // action bar title
        ActionBar ab = getSupportActionBar();

        if(ab!=null){
            ab.setTitle(name);
        }
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             DatabaseHelper MyDB = new DatabaseHelper(updateActivity.this);
             boolean res = MyDB.updateData(product_input.getText().toString(),product_name.getText().toString(),description.getText().toString());
             if(res==true){
                 Toast.makeText(updateActivity.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
             }else{
                 Toast.makeText(updateActivity.this, "Failed to update", Toast.LENGTH_SHORT).show();
             }
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });

    }
    void getIntentData(){
        if(getIntent().hasExtra("product_id") && getIntent().hasExtra("product_name")&&getIntent().hasExtra("description")){
            // get data form intent
            id=getIntent().getStringExtra("product_id");
            name=getIntent().getStringExtra("product_name");
            desc = getIntent().getStringExtra("description");

            //setting the intent data
            product_input.setText(id);
            product_name.setText(name);
            description.setText(desc);


        }else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete "+name);
        builder.setMessage("Are sure want to delete "+name+"?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               DatabaseHelper MyDB = new DatabaseHelper(updateActivity.this);
               boolean res = MyDB.deleteData(id);
               finish();

               if(res == true){
                   Toast.makeText(updateActivity.this, "Data deleted successfully", Toast.LENGTH_SHORT).show();
               }else{
                   Toast.makeText(updateActivity.this, "Failed to delete", Toast.LENGTH_SHORT).show();
               }

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}