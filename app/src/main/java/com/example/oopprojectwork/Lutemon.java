package com.example.oopprojectwork;

// class for creating luetemon objects
public class Lutemon {
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

    public Lutemon(String name, String color, int attack, int defense, int experience, int health, int id) {
        this.name = name;
        this.color = color;
        this.attack = attack;
        this.defense = defense;
        this.experience = experience;
    }

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

    public String getColor() {
        return color;
    }




}
