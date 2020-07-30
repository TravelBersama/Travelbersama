package com.example.travelbersama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    Database db;
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new Database(this);
        mTextUsername = (EditText)findViewById(R.id.username);
        mTextPassword = (EditText)findViewById(R.id.password);
        mTextCnfPassword = (EditText)findViewById(R.id.cnf_password);
        mButtonRegister = (Button)findViewById(R.id.button_register);
        mTextViewLogin = (TextView)findViewById(R.id.login);
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(Register.this,LoginActivity.class);
                startActivity(LoginIntent);
            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username = mTextUsername.getText().toString().trim();
                String Password = mTextPassword.getText().toString().trim();
                String Cnf_Password = mTextCnfPassword.getText().toString().trim();

                long val;
                if (mTextPassword.equals(mTextCnfPassword)) {
                }val = db.addUser(Username, Password);
                if (val > 0) {
                    Toast.makeText(Register.this, "Kamu Telah Terdaftar", Toast.LENGTH_SHORT).show();
                    Intent moveToLogin = new Intent(Register.this, LoginActivity.class);
                    startActivity(moveToLogin);
                } else {
                    Toast.makeText(Register.this, "Gagal Mendaftar", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
