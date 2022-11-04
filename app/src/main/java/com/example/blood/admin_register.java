package com.example.blood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Database.LoginHandler;

public class admin_register extends AppCompatActivity {
    EditText username, password, repassword;
    Button signup;
    LoginHandler DB;
    CheckBox terms;
    Boolean insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register);

        username = (EditText) findViewById(R.id.EmailAddress);
        password = (EditText) findViewById(R.id.Password);
        repassword = (EditText) findViewById(R.id.Password2);
        signup = (Button) findViewById(R.id.sign_up_btn);
        terms = (CheckBox) findViewById(R.id.TandC);
        DB = new LoginHandler(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                terms = findViewById(R.id.TandC);
                if (emailValidator(username) == true && PassValidator(password) == true) {

                    String user = username.getText().toString();
                    String pass = password.getText().toString();
                    String repass = repassword.getText().toString();

                    if (user.equals("") || pass.equals("") || repass.equals(""))
                        Toast.makeText(admin_register.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                    else {
                        if (pass.equals(repass)) {
                            Boolean checkuser = DB.checkusername(user);
                            if (checkuser == false) {
                                if(!terms.isChecked()) {
                                    Toast.makeText(admin_register.this, "Accept Terms And Conditions", Toast.LENGTH_LONG).show();
                                    insert = false;
                                }else
                                    insert = DB.insertData(user, pass);

                                if (insert == true) {
                                    Toast.makeText(admin_register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), admin_login.class);
                                    startActivity(intent);
                                }
                                else if(terms.isChecked())
                                    Toast.makeText(admin_register.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(admin_register.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                            }
                        } else{
                            Toast.makeText(admin_register.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else if (emailValidator(username) == false) {
                    Toast.makeText(admin_register.this, "Enter a Valid email", Toast.LENGTH_SHORT).show();
                } else if (PassValidator(password) == false)
                    Toast.makeText(getApplicationContext(), "Password must contain uppercase, lowercase and characters", Toast.LENGTH_SHORT).show();

            }
        });


        Button button1 = findViewById(R.id.btn_signIn);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), admin_login.class);
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
            // Toast.makeText(this, "Enter valid Email address !", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean PassValidator(EditText password) {
        String PassToText = password.getText().toString();
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=.*[a-z]).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(PassToText);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

}