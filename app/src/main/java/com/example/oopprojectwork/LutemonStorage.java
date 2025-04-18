package com.example.oopprojectwork;

import android.content.Context;

import com.example.oopprojectwork.Lutemon.Black;
import com.example.oopprojectwork.Lutemon.Green;
import com.example.oopprojectwork.Lutemon.Lutemon;
import com.example.oopprojectwork.Lutemon.Orange;
import com.example.oopprojectwork.Lutemon.Pink;
import com.example.oopprojectwork.Lutemon.Red;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class LutemonStorage {
    public static ArrayList<Lutemon> selectedForTraining = new ArrayList<>();
    public static ArrayList<Lutemon> selectedForBattle = new ArrayList<>();
    public static ArrayList<Lutemon> allLutemons = new ArrayList<>();


    // three built in lutemons
    static {
        allLutemons.add(new Red("scizer"));
        allLutemons.add(new Green("snivy"));
        allLutemons.add(new Pink("mew"));
    }
    public static void saveToFile(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput("lutemons.txt", Context.MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));

            for (Lutemon l : allLutemons) {
                String line = l.color() + "," + l.getName() + "," + l.getAttack()
                        + "," + l.getDefense() + "," + l.getHealth()+ "," +l.getExperience()
                        + "," + l.getImageResourceRight()+","+l.getImageResourceLeft()+","+l.getTotalBattles()
                        + "," + l.getWins();
                writer.write(line);
                writer.newLine();
            }


            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void loadFromFile(Context context) {
        allLutemons.clear(); // clear old data

        try {
            FileInputStream fis = context.openFileInput("lutemons.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                String color = parts[0];
                String name = parts[1];
                int atk = Integer.parseInt(parts[2]);
                int def = Integer.parseInt(parts[3]);
                int hp = Integer.parseInt(parts[4]);
                int exp = Integer.parseInt(parts[5]);
                int imageRight = Integer.parseInt(parts[6]);
                int imageLeft = Integer.parseInt(parts[7]);
                int totalBattles = Integer.parseInt(parts[8]);
                int wins = Integer.parseInt(parts[9]);

                Lutemon lutemon = null;

                switch (color) {
                    case "Red":
                        lutemon = new Red(name);
                        break;
                    case "Green":
                        lutemon = new Green(name);
                        break;
                    case "Pink":
                        lutemon = new Pink(name);
                        break;
                    case "Orange":
                        lutemon = new Orange(name);
                        break;
                    case "Black":
                        lutemon = new Black(name);
                        break;
                }

                if (lutemon != null) {
                    lutemon.setName(name);
                    lutemon.setAttack(atk);
                    lutemon.setDefense(def);
                    lutemon.setHealth(hp);
                    lutemon.setExperience(exp);
                    lutemon.setImageResourceRight(imageRight);
                    lutemon.setImageResourceLeft(imageLeft);
                    lutemon.setTotalBattles(totalBattles);
                    lutemon.setWins(wins);
                    allLutemons.add(lutemon);
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






}
