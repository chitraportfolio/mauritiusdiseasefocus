package com.example.mauritiusdiseasefocus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button registerButton;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //opening register new user activity
        registerButton = findViewById(R.id.btnRegister);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenRegisterNewUser();
            }
        });

        //opening registered user dashboard
        login = findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenRegisteredUserDashboard();
            }
        });




    }

    //method for opening registered user dashboard
    private void OpenRegisteredUserDashboard() {
        Intent intent  = new Intent(this, RegisteredUserDashboard.class);
        startActivity(intent);
    }

    //method for opening registered user dashboard
    private void OpenRegisterNewUser() {
        Intent intent  = new Intent(this, RegisterNewUser.class);
        startActivity(intent);
    }


}