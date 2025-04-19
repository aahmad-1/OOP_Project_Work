package com.example.oopprojectwork.Lutemon;

import java.io.Serializable;

// class for creating luetemon objects
public abstract class Lutemon implements Serializable {

    private int imageResourceRight;
    private int imageResourceLeft;
    private String name;
    private String color;
    private int maxHealth;
    private int attack;
    private int defense;
    private int experience;
    private int health;
    private int wins;
    private int losses;
    private int totalBattles;

    private int totalTrainings;




    public Lutemon(String name, int attack, int defense, int health, String color, int imageResourceRight, int imageResourceLeft) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.color = color;
        this.experience = 0;
        this.health = health;
        this.wins = 0;
        this.losses = 0;
        this.totalBattles = 0;
        this.totalTrainings = 0;
        this.imageResourceRight = imageResourceRight;
        this.imageResourceLeft = imageResourceLeft;
        this.maxHealth = health;



    }


    //commit
    public int getImageResourceRight() {
        return imageResourceRight;
    }

    public int getImageResourceLeft() {
        return imageResourceLeft;
    }

    public void setImageResourceRight(int imageResourceRight) {
        this.imageResourceRight = imageResourceRight;
    }
    public void setImageResourceLeft(int imageResourceLeft) {
        this.imageResourceLeft = imageResourceLeft;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void setName(String name){ this.name = name;}


    public void setTotalBattles(int totalBattles) {
        this.totalBattles = totalBattles;
    }


    public int getTotalTrainings() { return totalTrainings; }

    public void setTotalTrainings(int totalTrainings) {
        this.totalTrainings = totalTrainings;
    }

    public String color(){
        return color;
    }
    public void setColor(String color){
        this.color = color;
    }


    // Add getter for imageResource
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

    public int getTotalBattles() {
        return totalBattles;
    }

    public int getMaxHealth() {
        return maxHealth;
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

    @Override
    public String toString() {
        return name + " (" + color() + ") \nATK: " + attack + ", \nDEF: " + defense + ", \nHP: " + health + "\nEXP: " + experience;
    }


    public void setAttack(int atk) {this.attack = attack;
    }
    public void setDefense(int def) {this.defense = def;}

}
