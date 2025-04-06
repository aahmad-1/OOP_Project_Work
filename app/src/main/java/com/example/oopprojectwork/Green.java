package com.example.oopprojectwork;

public class Green extends Lutemon {
    public Green(String name, int attack, int defense, int experience, int health, int id, int wins, int losses, int totalBattles) {
        super(name, attack, defense, experience, health, id, wins, losses, totalBattles);
    }
    @Override
    public String getLutemonType() {
        return "Green";
    }
}
