package com.example.oopprojectwork;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oopprojectwork.Lutemon.Lutemon;

import java.io.Serializable;
import java.util.ArrayList;

public class BattleActivity extends AppCompatActivity {
    private Lutemon lutemon1;
    private Lutemon lutemon2;
    private TextView battleLogs;
    private TextView name1;
    private TextView name2;
    private TextView attackerInfo;
    private TextView defenderInfo;
    private Button btnNextAttack;
    private Button btnLutemonHome;
    ArrayList<Lutemon> lutemonsBattle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle_arena);
        name1 = findViewById(R.id.textView3);
        name2 = findViewById(R.id.textView4);
        attackerInfo = findViewById(R.id.battleInfoAttacker);
        defenderInfo = findViewById(R.id.battleInfoDefender);
        btnNextAttack = findViewById(R.id.btnNextAttack);
        btnLutemonHome = findViewById(R.id.btnLutemonHome);
        battleLogs = findViewById(R.id.battleLogs);

        lutemonsBattle = (ArrayList<Lutemon>) getIntent().getSerializableExtra("lutemons");
        lutemon1 = lutemonsBattle.get(0);
        lutemon2 = lutemonsBattle.get(1);
        name1.setText(lutemon1.getName());
        name2.setText(lutemon2.getName());
        attackerInfo.setText(lutemon1.toString());
        defenderInfo.setText(lutemon2.toString());



        btnNextAttack.setOnClickListener(view -> {

            Lutemon attacker;
            Lutemon defender;


            attacker = lutemon1;
            defender = lutemon2;

            // ⚔️ Calculate damage
            int damage = Math.max(attacker.getAttack() - defender.getDefense(), 0);
            int newHealth = Math.max(defender.getHealth() - damage, 0);
            defender.setHealth(newHealth);

            // 📝 Update UI
            attackerInfo.setText(attacker.toString());
            defenderInfo.setText(defender.toString());

            battleLogs.append(attacker.getName() + " attacked " + defender.getName() + " for " + damage + " damage.\n");

            // 💀 Check if defender is defeated
            if (defender.getHealth() <= 0) {
                battleLogs.append(defender.getName() + " has been defeated!\n");

                attacker.setExperience(attacker.getExperience() + 1);
                attacker.setWins(attacker.getWins() + 1);
                defender.setLosses(defender.getLosses() + 1);

                btnNextAttack.setEnabled(false);
                btnNextAttack.setText("Battle Over");
            } else {
                // 🔁 Switch turn
                attacker = lutemon2;
                defender = lutemon1;
            }
        });
        btnLutemonHome.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });














    }
}
