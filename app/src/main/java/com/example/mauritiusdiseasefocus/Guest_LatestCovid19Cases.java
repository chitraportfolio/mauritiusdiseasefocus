package com.example.mauritiusdiseasefocus;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Guest_LatestCovid19Cases extends AppCompatActivity {

    //declaring variables for connection with sqlserver
    private static String ip = "192.168.100.8";
    private static String port = "1433";
    private static String classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "MDF";
    private static String username = "test";
    private static String password = "1234";
    private static String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database+"";
    private Connection con = null;

    TextView txtLocalCases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gutest_latest_covid19_cases);

        //setting strictmode
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        try {
            Class.forName(classes);
            con = DriverManager.getConnection(url, username, password);
            Toast.makeText(Guest_LatestCovid19Cases.this, "Connection Successful", Toast.LENGTH_SHORT).show();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            Toast.makeText(Guest_LatestCovid19Cases.this, "Error connecting", Toast.LENGTH_SHORT).show();
        }

        txtLocalCases = findViewById(R.id.txtTotalLocalCases);


    }

    public void TotalLocalCases(){

    }
}