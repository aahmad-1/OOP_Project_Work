package com.example.oopprojectwork;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FragmentsActivity extends AppCompatActivity {

    Button btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);

        btnMenu = findViewById(R.id.btnGoToMenu);

        // Initial fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentFragmentContainer, new StatisticsFragment())
                .commit();

        // Set up buttons
        Button btnStats = findViewById(R.id.btnStatistics);
        Button btnCharts = findViewById(R.id.btnCharts);

        btnStats.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentFragmentContainer, new StatisticsFragment())
                    .commit();
        });

        btnCharts.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentFragmentContainer, new ChartsFragment())
                    .commit();
        });


        btnMenu.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}




