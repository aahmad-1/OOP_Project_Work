package com.example.oopprojectwork;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageView leftSword, rightSword;

    ProgressBar trainingProgress;
    View flashOverlay;

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
            // We're on lutemon_battle screen
            setupBattleScreenNavigation();
        } else if (findViewById(R.id.btnMoveToTraining) != null) {
            // We're on lutemon_home screen
            setupHomeScreenNavigation();
        } else if (findViewById(R.id.btnGoToHome) != null) {
            // We're on training_area_lutemon screen
            setupTrainingScreenNavigation();
        } else if (findViewById(R.id.btnNextAttack) != null) {
            // We're on battle_lutemon screen
            setupBattleLutemonNavigation();
        } else if (findViewById(R.id.btnCancel) != null) {
            setupCreateLutemonNavigation();
        } else if (findViewById(R.id.btnGoToMenu) != null) {
            // We're on statistics screen
            setupStatisticsScreenNavigation();
        }
        // Add more conditions if you have other screens
    }

    private void setupBattleScreenNavigation() {
        // Button from lutemon_battle to lutemon_home
        Button btnViewHome = findViewById(R.id.btnViewHome);
        btnViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        // Button from lutemon_battle to statistics
        Button btnViewTraining = findViewById(R.id.btnViewStatistics);
        btnViewTraining.setOnClickListener(v -> setContentView(R.layout.statistics));

        // Button from lutemon_battle to create_new_lutemon
        Button btnCreateNew = findViewById(R.id.btnCreateNewLutemon);
        btnCreateNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateNewLutemon.class);
                startActivity(intent);
            }
        });
    }

    private void setupHomeScreenNavigation() {
        // Button from lutemon_home to training_area_lutemon
        Button btnMoveToTraining = findViewById(R.id.btnMoveToTraining);
        btnMoveToTraining.setOnClickListener(v -> {
            setContentView(R.layout.training_area_lutemon);

            trainingProgress = findViewById(R.id.progressBar);  // ProgressBar from XML
            flashOverlay = findViewById(R.id.flashOverlay);
        });

        Button btnMoveToMenu = findViewById(R.id.btnMoveToMenu);
        btnMoveToMenu.setOnClickListener(v -> setContentView(R.layout.lutemon_menu));

        // Button from lutemon_home to battle_lutemon
        Button btnMoveToBattle = findViewById(R.id.btnMoveToBattle);
        btnMoveToBattle.setOnClickListener(v -> {
            setContentView(R.layout.battle_arena);
            // Initialize sword ImageViews immediately after setting the battle arena content
            leftSword = findViewById(R.id.imageView4);
            rightSword = findViewById(R.id.imageView5);
            setupBattleLutemonNavigation();
        });
    }

    private void setupTrainingScreenNavigation() {
        // Button from training_area_lutemon to go to home
        Button btnGoHome = findViewById(R.id.btnGoToHome);
        btnGoHome.setOnClickListener(v -> setContentView(R.layout.lutemon_home));

        // Train button (add your training logic here)
        Button btnTrain = findViewById(R.id.btnTrain);
        TextView trainingCompleteText = findViewById(R.id.textView10);
        btnTrain.setOnClickListener(v -> {
            // Add training functionality here
            AnimationManager.startTrainingAnimation(trainingProgress, flashOverlay,trainingCompleteText);
        });
    }

    private void setupBattleLutemonNavigation() {

        // Button from battle_lutemon to go to home
        Button btnGoHome = findViewById(R.id.btnLutemonHome);
        btnGoHome.setOnClickListener(v -> setContentView(R.layout.lutemon_home));

        // Next Attack button
        Button btnNextAttack = findViewById(R.id.btnNextAttack);
        btnNextAttack.setOnClickListener(v -> {
            if (leftSword != null && rightSword != null) {
                AnimationManager.playSwordClashAnimation(MainActivity.this, leftSword, rightSword);
            }
        });
    }
    private void setupCreateLutemonNavigation() {
        // Button from create_new_lutemon to go back to lutemon_battle
        Button btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(v -> setContentView(R.layout.lutemon_menu));

        // Create button (add your create logic here)
        Button btnCreate = findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(v -> {
            // Add create functionality here
        });
    }
    private void setupStatisticsScreenNavigation() {
        // Button from statistics to go back to menu
        Button btnGoToMenu = findViewById(R.id.btnGoToMenu);
        btnGoToMenu.setOnClickListener(v -> setContentView(R.layout.lutemon_menu));
    }
}