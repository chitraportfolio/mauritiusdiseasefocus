package com.example.mauritiusdiseasefocus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

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
    private TextInputLayout txtFirstName;
    private TextInputLayout txtLastName;
    private TextInputLayout txtEmail;
    private TextInputLayout txtMobile;
    private TextInputLayout txtUsername;
    private TextInputLayout txtConfirmPassword;
    private TextInputLayout txtPassword; //to check if password and confirm password matches

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_user);

        txtFirstName = (TextInputLayout) findViewById(R.id.edtTxtFirstName);
        txtLastName = (TextInputLayout) findViewById(R.id.edtTxtLastName);
        txtEmail = (TextInputLayout) findViewById(R.id.edtTextEmail);
        txtMobile = (TextInputLayout) findViewById(R.id.edtTextPhoneNumber);
        txtUsername = (TextInputLayout) findViewById(R.id.edtTextUsername);
        txtConfirmPassword = (TextInputLayout) findViewById(R.id.edtTextRegisterPasswordConfirmation);
        txtPassword = (TextInputLayout) findViewById(R.id.edtTextRegisterPassword);

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
        //if connection is successful, proceed
        if (con != null) {
            Statement statement = null;
            //check if firstname is blank
            if(txtFirstName.toString() ==null){
                txtFirstName.setError("Your first name is required!");
            }
            try {
                //todo: Check if username does not already exist in database before inserting
                //todo: check if first name, lastname, email address and phone number does not already exist in db before inserting
                //todo: password complexity shoud be more than 7 figures
                //todo: password and confirm password should match before saving
                statement = con.createStatement();
                String query = "exec dbo.MDF_InsertNewRegisteredUsers '" + txtFirstName.toString() + "','" + txtLastName.toString() + "','" + txtEmail.toString() + "','" + txtMobile.toString() + "','" + txtUsername.toString() + "', '" + txtConfirmPassword.toString() + "'";
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