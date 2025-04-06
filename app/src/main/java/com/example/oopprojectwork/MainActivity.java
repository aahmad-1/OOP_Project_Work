package com.example.oopprojectwork;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageView leftSword, rightSword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.lutemon_battle);
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
        } else if (findViewById(R.id.button4) != null) {
            // We're on lutemon_home screen
            setupHomeScreenNavigation();
        } else if (findViewById(R.id.button8) != null) {
            // We're on training_area_lutemon screen
            setupTrainingScreenNavigation();
        } else if (findViewById(R.id.button) != null) {
            // We're on battle_lutemon screen
            setupBattleLutemonNavigation();
        }
        // Add more conditions if you have other screens
    }

    private void setupBattleScreenNavigation() {
        // Button from lutemon_battle to lutemon_home
        Button btnViewHome = findViewById(R.id.btnViewHome);
        btnViewHome.setOnClickListener(v -> setContentView(R.layout.lutemon_home));

        // Button from lutemon_battle to training_area_lutemon
        Button btnViewTraining = findViewById(R.id.btnViewTrainingArea);
        btnViewTraining.setOnClickListener(v -> setContentView(R.layout.training_area_lutemon));

        // Button from lutemon_battle to battle_lutemon
        Button btnViewBattleArena = findViewById(R.id.btnViewBattleArena);
        btnViewBattleArena.setOnClickListener(v -> setContentView(R.layout.battle_arena));

        // Button from lutemon_battle to create_new_lutemon
        Button btnCreateNew = findViewById(R.id.btnCreateNewLutemon);
        btnCreateNew.setOnClickListener(v -> setContentView(R.layout.create_new_lutemon));
    }

    private void setupHomeScreenNavigation() {
        // Button from lutemon_home to training_area_lutemon
        Button btnMoveToTraining = findViewById(R.id.button4);
        btnMoveToTraining.setOnClickListener(v -> setContentView(R.layout.training_area_lutemon));

        // Button from lutemon_home to battle_lutemon
        Button btnMoveToBattle = findViewById(R.id.button5);
        btnMoveToBattle.setOnClickListener(v -> setContentView(R.layout.battle_arena));
    }

    private void setupTrainingScreenNavigation() {
        // Button from training_area_lutemon to go to home
        Button btnGoHome = findViewById(R.id.button9);
        btnGoHome.setOnClickListener(v -> setContentView(R.layout.lutemon_battle));

        // Train button (add your training logic here)
        Button btnTrain = findViewById(R.id.button8);
        btnTrain.setOnClickListener(v -> {
            // Add training functionality here
        });
    }

    private void setupBattleLutemonNavigation() {
        // Button from battle_lutemon to go to home
        Button btnGoHome = findViewById(R.id.button3);
        btnGoHome.setOnClickListener(v -> setContentView(R.layout.lutemon_battle));

        // Next Attack button (add your battle logic here)
        leftSword = findViewById(R.id.imageView4);
        rightSword = findViewById(R.id.imageView5);

        Button btnNextAttack = findViewById(R.id.button);
        btnNextAttack.setOnClickListener(v -> {
            // Add battle functionality here
            AnimationManager.playSwordClashAnimation(MainActivity.this, leftSword, rightSword);
        });
    }
}