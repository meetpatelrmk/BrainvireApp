package com.nirmit.brainvireapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nirmit.brainvireapp.usage.Common;
import com.nirmit.brainvireapp.usage.ValidationHandler;

public class LoginActivity extends AppCompatActivity {

    private EditText email,password;
    Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.editText_email);
        password = (EditText) findViewById(R.id.editText_password);
        signin = (Button) findViewById(R.id.btn_signin);
        signin.setOnClickListener(v -> {

            String userEmail = email.getText().toString().trim();
            String userPassword = password.getText().toString().trim();

            if (ValidationHandler.Validation(userEmail,userPassword))
            {
                Common.login(getApplicationContext());
                Intent intent = new Intent(LoginActivity.this, DataActivity.class);
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(),"Wrong Email & Password",Toast.LENGTH_LONG).show();
            }
        });
    }
}