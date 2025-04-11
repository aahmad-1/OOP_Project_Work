package com.example.oopprojectwork.Lutemon;

import java.io.Serializable;

// class for creating luetemon objects
public abstract class Lutemon implements Serializable {

    private int imageResource;
    private String name;
    private String color;
    private int attack;
    private int defense;
    private int experience;
    private int health;
    private int id;
    private int maxHealth;
    private int idCounter;
    private int lutemonImageResource;
    private int wins;
    private int losses;
    private int totalBattles;




    public Lutemon(String name, int attack, int defense, int health, String color, int imageResource) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.color = color;
        this.experience = 0;
        this.health = health;
        this.wins = 0;
        this.losses = 0;
        this.totalBattles = 0;
        this.imageResource = imageResource;


    }
    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void setTotalBattles(int totalBattles) {
        this.totalBattles = totalBattles;
    }
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }




    public String color(){
        return color;
    }
    public void setColor(String color){
        this.color = color;
    }


    // Add getter for imageResource
    public int getImageResource() {return imageResource;}
    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getExperience() {
        return experience;
    }

    public int getHealth() {
        return health;
    }

    public int getId() {
        return id;
    }
    public int getTotalBattles() {
        return totalBattles;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getIdCounter() {
        return idCounter;
    }

    public int getLutemonImageResource() {
        return lutemonImageResource;
    }


    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }
    public int getLosses() {
        return losses;
    }
    public void setExperience(int experience){
        this.experience = experience;
    }
    public void setHealth(int health){
        this.health = health;
    }






}
