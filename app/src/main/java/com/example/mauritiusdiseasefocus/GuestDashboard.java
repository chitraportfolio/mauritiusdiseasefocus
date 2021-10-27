package com.example.mauritiusdiseasefocus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class GuestDashboard extends AppCompatActivity {

    CardView latestCovidCases, govtRestrictions, otherDiseaseOutbreaks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_dashboard);

        latestCovidCases = findViewById(R.id.cdLatestCovidOutbreak);
        latestCovidCases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Open_Guest_LatestCovid19Cases();
            }
        });

        govtRestrictions = findViewById(R.id.cdDaily_Restrictions);
        govtRestrictions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGovtRestrictions();
            }
        });

        otherDiseaseOutbreaks = findViewById(R.id.cdOtherDiseaseOutbreaks);
        otherDiseaseOutbreaks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenOtherDiseaseOutbreaks();
            }
        });


    }

    private void OpenOtherDiseaseOutbreaks() {
        Intent intent  = new Intent(this, Guest_OtherDiseaseOutbreaks.class);
        startActivity(intent);

    }

    private void OpenGovtRestrictions() {
        Intent intent  = new Intent(this, Guest_DailyRestrictions.class);
        startActivity(intent);
    }

    private void Open_Guest_LatestCovid19Cases() {
        Intent intent  = new Intent(this, Guest_LatestCovid19Cases.class);
        startActivity(intent);
    }
}