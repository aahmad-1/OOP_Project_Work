package com.example.oopprojectwork;

import com.example.oopprojectwork.Lutemon.Lutemon;

public class BattleManager {
    public static void battle(Lutemon lutemon1, Lutemon lutemon2) {
        Lutemon lutemonAttacker = lutemon1;
        Lutemon lutemonDefender = lutemon2;


        while(true){
            int lutemonAttackerDamage = lutemonAttacker.getAttack() - lutemonDefender.getDefense();
            lutemonDefender.setHealth(lutemonDefender.getHealth() - lutemonAttackerDamage);

            if(lutemonDefender.getHealth() <= 0){
                lutemonAttacker.setExperience(lutemonAttacker.getExperience() + 1);
                lutemonAttacker.setWins(lutemonAttacker.getWins() + 1);
                lutemonDefender.setLosses(lutemonDefender.getLosses() + 1);
                lutemonDefender.setTotalBattles(lutemonDefender.getTotalBattles() + 1);
                break;
            }

            lutemonDefender = lutemonAttacker;
        }



    }

}
