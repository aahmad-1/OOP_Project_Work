package com.example.oopprojectwork.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    LutemonAdapter lutemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lutemon_home);

        recyclerView = findViewById(R.id.recyclerView);
        trainingBtn = findViewById(R.id.btnMoveToTraining);
        battleBtn = findViewById(R.id.btnMoveToBattle);
        menuBtn = findViewById(R.id.btnMoveToMenu);

        LutemonStorage.allLutemons.add(new Red("scizer"));
        LutemonStorage.allLutemons.add(new Green("snivy"));
        LutemonStorage.allLutemons.add(new Pink("mew"));
        LutemonStorage.allLutemons.add(new Orange("charmander"));
        LutemonStorage.allLutemons.add(new Black("umbreon")); // blah blah

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        lutemonAdapter = new LutemonAdapter(LutemonStorage.allLutemons,this);
        recyclerView.setAdapter(lutemonAdapter);


        trainingBtn.setOnClickListener(v -> {
            LutemonStorage.selectedForTraining = lutemonAdapter.getSelectedLutemons();
            if (LutemonStorage.selectedForTraining.size() == 1) {
                Intent intent = new Intent(this,TrainingActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Select at least one Lutemon!", Toast.LENGTH_SHORT).show();
            }

        });
        battleBtn.setOnClickListener(view -> {
            ArrayList<Lutemon> selected = lutemonAdapter.getSelectedLutemons();
            if (selected.size() == 2) {
                Intent intent = new Intent(this, BattleActivity.class);
                intent.putExtra("lutemons", selected);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Select exactly two Lutemons for battle!", Toast.LENGTH_SHORT).show();
            }
        });

        menuBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });


    }


}
