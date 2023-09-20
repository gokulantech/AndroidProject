package com.example.productcreation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.productcreation.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    DatabaseHelper DB;
    EditText editText,editText1,editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DB = new DatabaseHelper(this);

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.loginEmail.getText().toString();
                String user = binding.loginUser.getText().toString();
                String password = binding.loginPassword.getText().toString();
                if(email.equals("")||user.equals("")||password.equals("")){
                    Toast.makeText(LoginActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                }else{
                    boolean checkEmailOrPassword = DB.checkMailAndPassword(email,user,password);
                    editText = findViewById(R.id.login_email);
                    editText1= findViewById(R.id.login_user);
                    editText2 = findViewById(R.id.login_password);
                    if(checkEmailOrPassword==true){
                        editText.getText().clear();
                        editText1.getText().clear();
                        editText2.getText().clear();
                        Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        binding.SignupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),signUpActivity.class);
                startActivity(intent);
            }
        });
    }
}