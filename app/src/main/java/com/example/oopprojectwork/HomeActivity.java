package com.example.oopprojectwork;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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
        trainingBtn = findViewById(R.id.training_btn);
        battleBtn = findViewById(R.id.battle_btn);
        menuBtn = findViewById(R.id.menu_btn);

        LutemonStorage.allLutemons.clear();
    }


}
