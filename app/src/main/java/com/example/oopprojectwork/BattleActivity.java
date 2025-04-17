package com.example.oopprojectwork;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import java.util.Random;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oopprojectwork.Lutemon.Lutemon;

import java.util.ArrayList;

public class BattleActivity extends AppCompatActivity {

    private ImageView leftSword, rightSword, lutemonPic1, lutemonPic2;

    private Lutemon lutemon1;
    private Lutemon lutemon2;

    private ProgressBar healthBar1;
    private ProgressBar healthBar2;
    private TextView battleLogs;
    private TextView name1;
    private TextView name2;
    private TextView attackerInfo;
    private TextView defenderInfo;
    private Button btnNextAttack;
    private Button btnLutemonHome;
    private ArrayList<Lutemon> lutemonsBattle;
    private boolean isLutemon1Turn = true; // Track whose turn it is

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle_arena);

        leftSword = findViewById(R.id.imageView4);
        rightSword = findViewById(R.id.imageView5);

        healthBar1 = findViewById(R.id.healthBar1);
        healthBar2 = findViewById(R.id.healthBar2);

        name1 = findViewById(R.id.textView3);
        name2 = findViewById(R.id.textView4);
        attackerInfo = findViewById(R.id.battleInfoAttacker);
        defenderInfo = findViewById(R.id.battleInfoDefender);
        btnNextAttack = findViewById(R.id.btnNextAttack);
        btnLutemonHome = findViewById(R.id.btnLutemonHome);
        battleLogs = findViewById(R.id.battleLogs);

        lutemonPic1 = findViewById(R.id.imageView7);
        lutemonPic2 = findViewById(R.id.imageView8);

        lutemonsBattle = (ArrayList<Lutemon>) getIntent().getSerializableExtra("lutemons");
        lutemon1 = lutemonsBattle.get(0);
        lutemon2 = lutemonsBattle.get(1);

        name1.setText(lutemon1.getName());
        name2.setText(lutemon2.getName());
        attackerInfo.setText(lutemon1.toString());
        defenderInfo.setText(lutemon2.toString());
        lutemonPic1.setImageResource(lutemon1.getImageResource());
        lutemonPic2.setImageResource(lutemon2.getImageResource());

        healthBar1.setMax(lutemon1.getHealth());
        healthBar1.setProgress(lutemon1.getHealth());

        healthBar2.setMax(lutemon2.getHealth());
        healthBar2.setProgress(lutemon2.getHealth());

        // Create random
        Random random = new Random();

        btnNextAttack.setOnClickListener(view -> {
            Lutemon attacker;
            Lutemon defender;

            if (isLutemon1Turn) {
                attacker = lutemon1;
                defender = lutemon2;
            } else {
                attacker = lutemon2;
                defender = lutemon1;
            }

            attacker.setExperience(12);
            defender.setExperience(12);

            // 🛡️ Defender's Dodge Chance (before taking damage from attacker)
            boolean defenderDodged = false;
            if (defender.getExperience() > 10 && random.nextInt(5) == 1) { // 20% chance to dodge
                defenderDodged = true;
                battleLogs.append(defender.getName() + " DODGED the attack from " + attacker.getName() + "!\n");
            }

            // 🗡️ Attacker's Turn
            if (!defenderDodged) {
                if (attacker.getExperience() > 10 && random.nextInt(5) == 1) { // 20% chance for special attack
                    performSpecialAttack(attacker, defender);
                } else {
                    performNormalAttack(attacker, defender);
                }
            }

            // 🩹 Update health bar
            healthBar1.setProgress(lutemon1.getHealth());
            healthBar2.setProgress(lutemon2.getHealth());

            // 📝 Update UI
            attackerInfo.setText(lutemon1.toString());
            defenderInfo.setText(lutemon2.toString());

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
                isLutemon1Turn = !isLutemon1Turn;
            }
            AnimationManager.playSwordClashAnimation(BattleActivity.this, leftSword, rightSword);
        });

        btnLutemonHome.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    // Helper method for normal attack
    private void performNormalAttack(Lutemon attacker, Lutemon defender) {
        int damage = Math.max(attacker.getAttack() - defender.getDefense(), 0);
        int newHealth = Math.max(defender.getHealth() - damage, 0);
        defender.setHealth(newHealth);
        battleLogs.append(attacker.getName() + " attacked " + defender.getName() + " for " + damage + " damage.\n");
    }

    // Helper method for special attack
    private void performSpecialAttack(Lutemon attacker, Lutemon defender) {
        int specialDamage = Math.max(attacker.getAttack() * 2 - defender.getDefense(), 0);
        int newHealth = Math.max(defender.getHealth() - specialDamage, 0);
        defender.setHealth(newHealth);
        battleLogs.append(attacker.getName() + " used a SPECIAL ATTACK on " + defender.getName() + " for " + specialDamage + " damage.\n");
    }
}