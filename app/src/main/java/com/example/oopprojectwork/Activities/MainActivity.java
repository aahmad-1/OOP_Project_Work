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
    }

    private void setupBattleScreenNavigation() {
        // Button from lutemon_battle to lutemon_home
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

    private void setupHomeScreenNavigation() {
        // Button from lutemon_home to training_area_lutemon
        Button btnMoveToTraining = findViewById(R.id.btnMoveToTraining);
        btnMoveToTraining.setOnClickListener(v -> {
            setContentView(R.layout.training_area_lutemon);
            trainingProgress = findViewById(R.id.progressBar);
            flashOverlay = findViewById(R.id.flashOverlay);
        });

        Button btnMoveToMenu = findViewById(R.id.btnMoveToMenu);
        btnMoveToMenu.setOnClickListener(v -> setContentView(R.layout.lutemon_menu));

        // Button from lutemon_home to battle_lutemon
        Button btnMoveToBattle = findViewById(R.id.btnMoveToBattle);
        btnMoveToBattle.setOnClickListener(v -> {
            setContentView(R.layout.battle_arena);
            setupBattleLutemonNavigation();
        });
    }

    private void setupTrainingScreenNavigation() {
        // Button from training_area_lutemon to go to home
        Button btnGoHome = findViewById(R.id.btnGoToHome);
        btnGoHome.setOnClickListener(v -> setContentView(R.layout.lutemon_home));

        // Train button
        Button btnTrain = findViewById(R.id.btnTrain);
        TextView trainingCompleteText = findViewById(R.id.trainingCompleteText);
        btnTrain.setOnClickListener(v -> {
            // Updated to include a Runnable parameter
            AnimationManager.startTrainingAnimation(trainingProgress, flashOverlay, trainingCompleteText, () -> {
                // This is the callback that runs when animation completes
                // You can add any post-animation logic here if needed
            });
        });
    }

    private void setupBattleLutemonNavigation() {
        // Button from battle_lutemon to go to home
        Button btnGoHome = findViewById(R.id.btnLutemonHome);
        btnGoHome.setOnClickListener(v -> setContentView(R.layout.lutemon_home));

        // Next Attack button
        Button btnNextAttack = findViewById(R.id.btnNextAttack);
        btnNextAttack.setOnClickListener(v -> {
            // Add battle functionality here
        });
    }

    private void setupCreateLutemonNavigation() {
        // Button from create_new_lutemon to go back to lutemon_battle
        Button btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(v -> setContentView(R.layout.lutemon_menu));

        // Create button
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