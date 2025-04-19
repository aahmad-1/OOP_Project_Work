package com.example.oopprojectwork.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.oopprojectwork.AnimationManager;
import com.example.oopprojectwork.Lutemon.Lutemon;
import com.example.oopprojectwork.LutemonStorage;
import com.example.oopprojectwork.R;

import java.util.ArrayList;

public class TrainingActivity extends Activity {
    Button btnTrain;
    Button btnGoToHome;
    ImageView imageView, imageView3;
    ProgressBar progressBar;
    View flashOverlay;
    TextView LutemonTrainingName, LutemontrainingInfo, trainingCompleteText;
    TextView DodgeAbility, SpecialAttackAbility;
    Lutemon trainingLutemon;

    // Track if abilities have been unlocked to prevent repeated notifications
    private boolean dodgeUnlocked = false;
    private boolean specialAttackUnlocked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_area_lutemon);

        btnTrain = findViewById(R.id.btnTrain);
        btnGoToHome = findViewById(R.id.btnGoToHome);
        imageView3 = findViewById(R.id.imageView3);
        imageView = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progressBar);
        flashOverlay = findViewById(R.id.flashOverlay);
        LutemonTrainingName = findViewById(R.id.LutemonTrainingName);
        LutemontrainingInfo = findViewById(R.id.LutemontrainingInfo);
        trainingCompleteText = findViewById(R.id.trainingCompleteText);
        DodgeAbility = findViewById(R.id.DodgeAbility);
        SpecialAttackAbility = findViewById(R.id.SpecialAttackAbility);

        ArrayList<Lutemon> trainingLutemon1 = LutemonStorage.selectedForTraining;
        trainingLutemon = trainingLutemon1.get(0);

        // Initialize ability flags based on current experience
        checkInitialAbilityStatus();
        updateLutemonInfo();

        btnTrain.setOnClickListener(view -> {
            AnimationManager.startTrainingAnimation(progressBar, flashOverlay, trainingCompleteText, () -> {
                // Update experience and training stats after the animation ends
                int previousExp = trainingLutemon.getExperience();
                trainingLutemon.setExperience(previousExp + 2);
                trainingLutemon.setTotalTrainings(trainingLutemon.getTotalTrainings() + 1);


                // Check if new abilities should be unlocked
                checkAndShowNewAbilities(previousExp);

                // Save the updated data
                LutemonStorage.saveToFile(this);

                // Update the UI with the new Lutemon info
                updateLutemonInfo();
            });
        });

        btnGoToHome.setOnClickListener(view -> {
            Intent intent = new Intent(TrainingActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }

    private void updateLutemonInfo() {
        LutemonTrainingName.setText(trainingLutemon.getName());
        LutemontrainingInfo.setText("ATK: " + trainingLutemon.getAttack() + ",\n" +
                "DEF: " + trainingLutemon.getDefense() + ",\n" +
                "HP: " + trainingLutemon.getHealth() + ",\n" +
                "EXP: " + trainingLutemon.getExperience());
        imageView.setImageResource(trainingLutemon.getImageResourceRight());
        imageView3.setImageResource(trainingLutemon.getImageResourceRight());
    }

    private void checkInitialAbilityStatus() {
        int currentExp = trainingLutemon.getExperience();

        // Set initial state of ability flags
        dodgeUnlocked = currentExp >= 4;
        specialAttackUnlocked = currentExp >= 6;
    }

    private void checkAndShowNewAbilities(int previousExp) {
        int currentExp = trainingLutemon.getExperience();

        // Hide any previously shown ability messages
        DodgeAbility.setVisibility(View.GONE);
        SpecialAttackAbility.setVisibility(View.GONE);

        // Check for DODGE ability unlocked in this training session
        if (!dodgeUnlocked && previousExp < 4 && currentExp >= 4) {
            dodgeUnlocked = true;
            showAbilityUnlocked(DodgeAbility);
        }

        // Check for SPECIAL ATTACK ability unlocked in this training session
        if (!specialAttackUnlocked && previousExp < 6 && currentExp >= 6) {
            specialAttackUnlocked = true;
            showAbilityUnlocked(SpecialAttackAbility);
        }
    }

    private void showAbilityUnlocked(TextView abilityTextView) {
        // Setting up the animation for the ability text
        abilityTextView.setScaleX(0f);
        abilityTextView.setScaleY(0f);
        abilityTextView.setAlpha(0f);
        abilityTextView.setVisibility(View.VISIBLE);

        // Animate the appearance with the same animation style as training complete text
        abilityTextView.animate()
                .scaleX(1f)
                .scaleY(1f)
                .alpha(1f)
                .setDuration(600)
                .setInterpolator(new android.view.animation.OvershootInterpolator())
                .start();

        // The hiding will be handled by the same mechanism that hides the training complete text
        // in AnimationManager.startTrainingAnimation (after 3 seconds)
    }
}