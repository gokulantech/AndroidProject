package com.example.productcreation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.productcreation.databinding.ActivitySignUpBinding;

public class signUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DB  = new DatabaseHelper(this);
        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.signUpEmail.getText().toString();
                String user = binding.signUpUser.getText().toString();
                String password = binding.signUpPassword.getText().toString();
                String phone = binding.signUpPhone.getText().toString();
                if(email.equals("")||user.equals("")||password.equals("")||phone.equals("")){
                    Toast.makeText(signUpActivity.this, "All fields are Mandatory", Toast.LENGTH_SHORT).show();
                }else{
                    boolean checkUserEmail = DB.checkMail(email);
                    if(checkUserEmail==false){
                       boolean insert = DB.insertData(email,user,password,phone);
                       if(insert==true){
                           Toast.makeText(signUpActivity.this, "Signup successfully", Toast.LENGTH_SHORT).show();
                           Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                           startActivity(intent);
                       }else{
                           Toast.makeText(signUpActivity.this, "Signup failed", Toast.LENGTH_SHORT).show();
                       }
                    }else{
                        Toast.makeText(signUpActivity.this, "User already existed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}