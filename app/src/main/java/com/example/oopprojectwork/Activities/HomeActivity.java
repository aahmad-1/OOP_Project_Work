package com.example.oopprojectwork.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oopprojectwork.Activities.BattleActivity;
import com.example.oopprojectwork.Lutemon.Lutemon;
import com.example.oopprojectwork.Lutemon.Red;
import com.example.oopprojectwork.Lutemon.Green;
import com.example.oopprojectwork.Lutemon.Pink;
import com.example.oopprojectwork.Lutemon.Orange;
import com.example.oopprojectwork.Lutemon.Black;
import com.example.oopprojectwork.LutemonAdapter;
import com.example.oopprojectwork.LutemonStorage;
import com.example.oopprojectwork.R;


import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button trainingBtn;
    Button battleBtn;
    Button menuBtn;
    Button deletebtn;
    LutemonAdapter lutemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lutemon_home);

        // In HomeActivity or MainActivity onCreate()

        recyclerView = findViewById(R.id.recyclerView);
        trainingBtn = findViewById(R.id.btnMoveToTraining);
        battleBtn = findViewById(R.id.btnMoveToBattle);
        menuBtn = findViewById(R.id.btnMoveToMenu);
        deletebtn = findViewById(R.id.button69);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        lutemonAdapter = new LutemonAdapter(LutemonStorage.allLutemons,this);
        recyclerView.setAdapter(lutemonAdapter);
        
        
        trainingBtn.setOnClickListener(v -> {
            LutemonStorage.selectedForTraining = lutemonAdapter.getSelectedLutemons();
            if (LutemonStorage.selectedForTraining.size() == 1) {
                Intent intent = new Intent(this,TrainingActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Select exactly 1 to Train!", Toast.LENGTH_SHORT).show();
            }

        });
        battleBtn.setOnClickListener(view -> {
            LutemonStorage.selectedForBattle = lutemonAdapter.getSelectedLutemons();
            if (LutemonStorage.selectedForBattle.size() == 2) {
                Intent intent = new Intent(this, BattleActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Select exactly two Lutemons for battle!", Toast.LENGTH_SHORT).show();
            }
        });

        deletebtn.setOnClickListener(view -> {
            ArrayList<Lutemon> selectedLutemons = lutemonAdapter.getSelectedLutemons();
            if (selectedLutemons.size() > 0) {
                for (Lutemon lutemon : selectedLutemons) {
                    LutemonStorage.allLutemons.remove(lutemon);
                }
                Toast.makeText(this,"Lutemon(s) deleted!", Toast.LENGTH_SHORT).show();

                // Clear the selected list in the adapter
                lutemonAdapter.getSelectedLutemons().clear();
                lutemonAdapter.notifyDataSetChanged();
                LutemonStorage.saveToFile(this);
            } else {
                Toast.makeText(this, "Select at least one Lutemon to delete!", Toast.LENGTH_SHORT).show();
            }
        } );


        menuBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });


    }


}
