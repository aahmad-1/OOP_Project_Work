package com.example.oopprojectwork.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.oopprojectwork.AnimationManager;
import com.example.oopprojectwork.LutemonStorage;
import com.example.oopprojectwork.R;

public class MainActivity extends AppCompatActivity {

    TextView counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.lutemon_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        LutemonStorage.loadFromFile(this);

        setupCurrentScreenNavigation();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        setupCurrentScreenNavigation();
    }

    private void setupCurrentScreenNavigation() {
        // Check which layout is currently displayed and setup appropriate navigation
        if (findViewById(R.id.btnViewHome) != null) {
            counter = findViewById(R.id.textLutemonsAtHomeCount);
            counter.setText("You have " + LutemonStorage.allLutemons.size() + " lutemons at home");
        }

        Button btnViewHome = findViewById(R.id.btnViewHome);
        btnViewHome.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        // Button from lutemon_battle to statistics
        Button btnViewTraining = findViewById(R.id.btnViewStatistics);
        btnViewTraining.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FragmentsActivity.class);
            startActivity(intent);
        });

        // Button from lutemon_battle to create_new_lutemon
        Button btnCreateNew = findViewById(R.id.btnCreateNewLutemon);
        btnCreateNew.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateNewLutemon.class);
            startActivity(intent);
        });
    }
}