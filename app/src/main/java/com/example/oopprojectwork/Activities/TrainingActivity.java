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
    ImageView imageView,imageView3;
    ProgressBar progressBar;
    View flashOverlay;
    TextView LutemonTrainingName, LutemontrainingInfo, trainingCompleteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_area_lutemon);

        btnTrain = findViewById(R.id.btnTrain);
        btnGoToHome = findViewById(R.id.btnGoToHome);
        imageView3 = findViewById(R.id.imageView3);
        imageView = findViewById(R.id.imageView);
        progressBar= findViewById(R.id.progressBar);
        flashOverlay = findViewById(R.id.flashOverlay);
        LutemonTrainingName = findViewById(R.id.LutemonTrainingName);
        LutemontrainingInfo = findViewById(R.id.LutemontrainingInfo);
        trainingCompleteText = findViewById(R.id.trainingCompleteText);

        ArrayList<Lutemon> trainingLutemon1 = LutemonStorage.selectedForTraining;
        Lutemon trainingLutemon = trainingLutemon1.get(0);

        LutemonTrainingName.setText(trainingLutemon.getName());
        LutemontrainingInfo.setText(trainingLutemon.toString());
        imageView.setImageResource(trainingLutemon.getImageResourceRight());
        imageView3.setImageResource(trainingLutemon.getImageResourceRight());

        btnTrain.setOnClickListener(view -> {
            // Start the animation!
            AnimationManager.startTrainingAnimation(progressBar, flashOverlay, trainingCompleteText);

            // Also add experience to the Lutemon (if you want this part to be delayed, move it inside onAnimationEnd)
            trainingLutemon.setExperience(trainingLutemon.getExperience() + 2);
            trainingLutemon.setTotalTrainings(trainingLutemon.getTotalTrainings() + 1);
            Lutemon.trainingCounter++;
        });


        btnGoToHome.setOnClickListener(view ->{
            Intent intent = new Intent(TrainingActivity.this, HomeActivity.class);
            startActivity(intent);
        });



    }
}
