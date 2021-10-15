package com.example.mauritiusdiseasefocus;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterNewUser extends AppCompatActivity {

    //declaring variables for connection with sqlserver
    private static String ip = "192.168.100.8";
    private static String port = "1433";
    private static String classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "MDF";
    private static String username = "test";
    private static String password = "1234";
    private static String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database+"";
    private Connection con = null;

    //declaring variables for textview and button
    private TextView txtFirstName;
    private TextView txtLastName;
    private TextView txtEmail;
    private TextView txtMobile;
    private TextView txtUsername;
    private TextView txtConfirmPassword;
    private TextView txtPassword; //to check if password and confirm password matches

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_user);

        txtFirstName = findViewById(R.id.edtTxtFirstName);
        txtLastName = findViewById(R.id.edtTxtLastName);
        txtEmail = findViewById(R.id.edtTextEmail);
        txtMobile = findViewById(R.id.edtTextPhoneNumber);
        txtUsername = findViewById(R.id.edtTextUsername);
        txtConfirmPassword = findViewById(R.id.edtTextRegisterPasswordConfirmation);
        txtPassword = findViewById(R.id.edtTextPassword);

        //setting strictmode
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(classes);
            con = DriverManager.getConnection(url, username, password);
            Toast.makeText(RegisterNewUser.this, "Connection Successful", Toast.LENGTH_SHORT).show();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            Toast.makeText(RegisterNewUser.this, "Error connecting", Toast.LENGTH_SHORT).show();
        }

    }

    public void RegisterNewUser(View view) {
        //declaring strings to check for empty string and other conditions
        String firstName = txtFirstName.getText().toString();
        String lastName = txtLastName.getText().toString();
        String email = txtEmail.getText().toString();
        String mobile = txtMobile.getText().toString();
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        String confirmPassword = txtConfirmPassword.getText().toString();
        if(TextUtils.isEmpty(firstName)) {
            Toast.makeText(this, "You cannot omit your first name!", Toast.LENGTH_SHORT).show();
            txtFirstName.setBackgroundColor(Color.RED);
            return;
        }else if(TextUtils.isEmpty(lastName)) {
            Toast.makeText(this, "You cannot omit your last name!", Toast.LENGTH_SHORT).show();
            txtFirstName.setBackgroundColor(Color.RED);
            return;
        }else if(TextUtils.isEmpty(email)) {
            Toast.makeText(this, "You must insert your email!", Toast.LENGTH_SHORT).show();
            txtFirstName.setBackgroundColor(Color.RED);
            return;
        }else if(TextUtils.isEmpty(mobile)) {
            Toast.makeText(this, "You must insert your mobile number!", Toast.LENGTH_SHORT).show();
            txtFirstName.setBackgroundColor(Color.RED);
            return;
        }else if(TextUtils.isEmpty(username)) {
            Toast.makeText(this, "You must insert a password!", Toast.LENGTH_SHORT).show();
            txtFirstName.setBackgroundColor(Color.RED);
            return;
        }else if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "You must insert a password!", Toast.LENGTH_SHORT).show();
            txtFirstName.setBackgroundColor(Color.RED);
            return;
        }else if(TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "You must confirm your password!", Toast.LENGTH_SHORT).show();
            txtFirstName.setBackgroundColor(Color.RED);
            return;
        }else if(password != confirmPassword) {
            Toast.makeText(this, "Your password does not match! Please try again", Toast.LENGTH_SHORT).show();
            txtFirstName.setBackgroundColor(Color.RED);
            return;
        }  //if connection is successful, proceed
        if (con != null) {
            Statement statement = null;
            try {
                //todo: Check if username does not already exist in database before inserting
                //todo: check if first name, lastname, email address and phone number does not already exist in db before inserting
                //todo: password complexity should be more than 7 figures
                //todo: password and confirm password should match before saving
                statement = con.createStatement();
                String query = "exec dbo.MDF_InsertNewRegisteredUsers '" + txtFirstName.getText().toString() + "','" + txtLastName.getText().toString() + "','" + txtEmail.getText().toString() + "','" + txtMobile.getText().toString() + "','" + txtUsername.getText().toString() + "', '" + txtConfirmPassword.getText().toString() + "'";
                ResultSet resultSet = statement.executeQuery(query);
                Toast.makeText(RegisterNewUser.this, "Successfully Saved!", Toast.LENGTH_LONG).show();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(RegisterNewUser.this, "Error in connecting", Toast.LENGTH_SHORT).show();
        }

    }
}