package com.example.blood;

import static java.lang.Boolean.TRUE;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Database.LoginHandler;


public class admin_login extends AppCompatActivity {
    EditText username, password;
    Button login;
    LoginHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        username = (EditText) findViewById(R.id.EmailAddress);
        password = (EditText) findViewById(R.id.Password);
        login = (Button) findViewById(R.id.sign_up_btn);
        DB = new LoginHandler(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emailValidator(username) == TRUE) {

                    String user = username.getText().toString();
                    String pass = password.getText().toString();

                    // check if the inputs are empty if empty will show a toast message
                    if (user.equals("") && pass.equals(""))
                        Toast.makeText(admin_login.this, "Please fill the user name or password", Toast.LENGTH_LONG).show();
                    else {
                        Boolean checkuserpass = DB.checkusernamepassword(user, pass);// calls our login controller from db folder and check if the given username and pw exists on db
                        // admin login check
                        //username admin@blueblood.com  PW Blueblood@123
                        if (checkuserpass == true && user.equals("admin@blueblood.com")) {
                            Toast.makeText(admin_login.this, "Admin login successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), admin_home.class);
                            startActivity(intent);
                        }
                        // all other user login check
                        else if (checkuserpass == true) {
                            Toast.makeText(admin_login.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(getApplicationContext(), HomePage.class);
                            intent1.putExtra("email", user.trim());
                            startActivity(intent1);
                        } else {
                            Toast.makeText(admin_login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    Toast.makeText(admin_login.this, "Enter a valid email", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button button1 = findViewById(R.id.Btn_signUp);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), admin_register.class);
                startActivity(i);
            }
        });
    }

    private boolean emailValidator(EditText username) {
        String emailToText = username.getText().toString();

        if (!emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
                return true;
        } else {
            return false;
        }

    }
}