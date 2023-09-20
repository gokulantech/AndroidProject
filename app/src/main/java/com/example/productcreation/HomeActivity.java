package com.example.productcreation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.productcreation.databinding.ActivityHomeBinding;
import com.example.productcreation.databinding.ActivityLoginBinding;
import com.example.productcreation.databinding.ActivityMainBinding;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding homeBinding;
    DatabaseHelper MYdB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(homeBinding.getRoot());
        MYdB = new DatabaseHelper(this);
        homeBinding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String product_id = homeBinding.productId.getText().toString();
                String product_name = homeBinding.productName.getText().toString();
                String description = homeBinding.description.getText().toString();
                if(product_id.equals("")||product_name.equals("")||description.equals("")){
                    Toast.makeText(HomeActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                }else{
                    boolean insertData = MYdB.addProduct(product_id,product_name,description);
                    if(insertData==true){
                        Toast.makeText(HomeActivity.this, "product data added successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(HomeActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}