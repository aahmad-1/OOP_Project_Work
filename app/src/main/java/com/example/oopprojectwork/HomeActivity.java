package com.example.oopprojectwork;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oopprojectwork.Lutemon.Lutemon;
import com.example.oopprojectwork.Lutemon.Red;
import com.example.oopprojectwork.Lutemon.Green;
import com.example.oopprojectwork.Lutemon.Pink;
import com.example.oopprojectwork.Lutemon.Orange;
import com.example.oopprojectwork.Lutemon.Black;


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

        LutemonStorage.allLutemons.add(new Red("pikachu"));
        LutemonStorage.allLutemons.add(new Green("charmander"));
        LutemonStorage.allLutemons.add(new Pink("squirtle"));
        LutemonStorage.allLutemons.add(new Orange("bulbasaur"));
        LutemonStorage.allLutemons.add(new Black("jigglypuff"));

        lutemonAdapter = new LutemonAdapter(LutemonStorage.allLutemons,this);
        recyclerView.setAdapter(lutemonAdapter);

        trainingBtn.setOnClickListener(v -> {
            ArrayList<Lutemon> selectedLutemons = lutemonAdapter.getSelectedLutemons();
            if (selectedLutemons.size() == 1) {
                //LutemonStorage.selected


            }
        });

    }


}
