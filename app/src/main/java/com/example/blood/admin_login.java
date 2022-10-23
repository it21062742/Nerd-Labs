package com.example.blood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Database.Login;


public class admin_login extends AppCompatActivity {
        EditText username, password;
        Button login;
        Login DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        username = (EditText) findViewById(R.id.EmailAddress);
        password = (EditText) findViewById(R.id.Password);
        login = (Button) findViewById(R.id.sign_up_btn);
        DB = new Login(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("admin@blueblood.com") && pass.equals("Bleeding@123")){
                    Toast.makeText(admin_login.this, "Admin login successful", Toast.LENGTH_SHORT).show();
                    Intent intent  = new Intent(getApplicationContext(), admin_home.class);
                    startActivity(intent);}
                if(user.equals("") && pass.equals(""))
                    Toast.makeText(admin_login.this, "Please fill the user name or password", Toast.LENGTH_LONG).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(admin_login.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                        Intent intent1  = new Intent(getApplicationContext(), HomePage.class);
                        startActivity(intent1);
                    }else{
                        Toast.makeText(admin_login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

            Button button1 =findViewById(R.id.Btn_signUp);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),admin_register.class);
                startActivity(i);
            }
        });
    }
}