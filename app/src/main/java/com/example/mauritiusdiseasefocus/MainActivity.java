package com.example.mauritiusdiseasefocus;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    private Button registerButton;
    private Button login;
    private EditText edtTxtLogin, edtTxtpassword;

    //declaring variables for connection with sqlserver
    private static String loginIP = "192.168.100.8";
    private static String loginPort = "1433";
    private static String loginClasses = "net.sourceforge.jtds.jdbc.Driver";
    private static String loginDatabase = "MDF";
    private static String loginUsername = "test";
    private static String loginPassword = "1234";
    private static String loginURL = "jdbc:jtds:sqlserver://"+loginIP+":"+loginPort+"/"+loginDatabase+"";
    private Connection con = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTxtLogin = findViewById(R.id.edtTxtLoginID);
        edtTxtpassword = findViewById(R.id.edtTextPassword);

        //setting strictmode
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(loginClasses);
            con = DriverManager.getConnection(loginURL, loginUsername, loginPassword);
            Toast.makeText(MainActivity.this, "Connection Successful", Toast.LENGTH_SHORT).show();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Error connecting", Toast.LENGTH_SHORT).show();
        }

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
                Login();
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

    //method for login to the app
    public void Login(){
        //declaring strings to check for empty string and other conditions
        String loginID = edtTxtLogin.getText().toString();
        String loginPassword = edtTxtpassword.getText().toString();
        if(TextUtils.isEmpty(loginID)) {
            Toast.makeText(this, "Please enter your login ID!", Toast.LENGTH_SHORT).show();
            edtTxtLogin.setBackgroundColor(Color.RED);
            return;
        }else if(TextUtils.isEmpty(loginPassword)) {
            Toast.makeText(this, "You cannot omit your password!", Toast.LENGTH_SHORT).show();
            edtTxtpassword.setBackgroundColor(Color.RED);
            return;
        }else{
            edtTxtLogin.setBackgroundColor(Color.WHITE);
            edtTxtpassword.setBackgroundColor(Color.WHITE);
            //if connection is successful, proceed
            if (con != null) {
                Statement statement = null;
                try {
                    statement = con.createStatement();
                    String query = "exec dbo.MDF_Login '" + edtTxtLogin.getText().toString() + "','" + edtTxtpassword.getText().toString();
                    ResultSet resultSet = statement.executeQuery(query);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.drawable.ic_check);
                builder.setTitle("Login Successful");
                builder.setMessage("Welcome to Mauritius Disease Focus App!");
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                OpenRegisteredUserDashboard();
            } else {
                Toast.makeText(MainActivity.this, "Error in connecting", Toast.LENGTH_SHORT).show();
            }
        }
    }


}