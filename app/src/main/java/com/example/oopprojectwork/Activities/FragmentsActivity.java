package com.example.oopprojectwork.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oopprojectwork.Fragments.BarGraphFragment;
import com.example.oopprojectwork.Fragments.PieChartsFragment;
import com.example.oopprojectwork.R;
import com.example.oopprojectwork.Fragments.StatisticsFragment;

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
        Button btnPieCharts = findViewById(R.id.btnPieChart);
        Button btnBarGraph = findViewById(R.id.btnBarGraph);


        btnStats.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentFragmentContainer, new StatisticsFragment())
                    .commit();
        });

        btnPieCharts.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentFragmentContainer, new PieChartsFragment())
                    .commit();
        });

        btnBarGraph.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentFragmentContainer, new BarGraphFragment())
                    .commit();
        });


        btnMenu.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}




