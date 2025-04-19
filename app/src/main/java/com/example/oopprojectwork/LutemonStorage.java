package com.example.oopprojectwork;

import static com.example.oopprojectwork.Lutemon.Lutemon.battleCounter;
import static com.example.oopprojectwork.Lutemon.Lutemon.trainingCounter;

import android.content.Context;
import android.util.Log;

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
    private static final String TAG = "LutemonStorage";
    private static final String FILENAME = "lutemons.txt";
    private static final int EXPECTED_FIELD_COUNT = 11;

    public static ArrayList<Lutemon> selectedForTraining = new ArrayList<>();
    public static ArrayList<Lutemon> selectedForBattle = new ArrayList<>();
    public static ArrayList<Lutemon> allLutemons = new ArrayList<>();

    // Initialize with default Lutemons
    static {
        allLutemons.add(new Red("scizer"));
        allLutemons.add(new Green("snivy"));
        allLutemons.add(new Pink("mew"));
    }

    public static void saveToFile(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput("lutemons.txt", Context.MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            writer.write("BATTLE_COUNTER=" + battleCounter);
            writer.newLine();
            writer.write("TRAINING_COUNTER=" + trainingCounter);
            writer.newLine();

            for (Lutemon l : allLutemons) {
                String line = l.color() +","+ l.getName() +","+ l.getAttack() +","+ l.getDefense()
                        +"," +l.getHealth() +","+ l.getExperience() +"," +l.getImageResourceRight()
                        +","+ l.getImageResourceLeft() +","+ l.getTotalBattles() +","+ l.getWins() +","+l.getTotalTrainings();
                writer.write(line);
                writer.newLine();
            }



            writer.close();
        } catch (IOException e) {
            Log.e(TAG, "Error saving Lutemons to file", e);
        }
    }

    public static void loadFromFile(Context context) {
        allLutemons.clear(); // clear old data
        battleCounter = 0;
        trainingCounter = 0;

        try {
            FileInputStream fis = context.openFileInput("lutemons.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String line;
            while ((line = reader.readLine()) != null) {

                if (line.startsWith("BATTLE_COUNTER=")) {
                    battleCounter = Integer.parseInt(line.split("=")[1]);
                    continue;
                } else if (line.startsWith("TRAINING_COUNTER=")) {
                    trainingCounter = Integer.parseInt(line.split("=")[1]);
                    continue;
                }

                String[] parts = line.split(",");

                // Skip invalid lines
                if (parts.length != EXPECTED_FIELD_COUNT) {
                    Log.w(TAG, "Skipping invalid data line: " + line);
                    continue;
                }

                try {
                    Lutemon lutemon = createLutemonFromParts(parts);
                    if (lutemon != null) {
                        allLutemons.add(lutemon);
                    }
                } catch (NumberFormatException e) {
                    Log.e(TAG, "Error parsing Lutemon data: " + line, e);
                }
            }
        } catch (FileNotFoundException e) {
            // File doesn't exist yet - that's okay, we'll use default Lutemons
            Log.i(TAG, "No save file found - using default Lutemons");
        } catch (IOException e) {
            Log.e(TAG, "Error loading Lutemons from file", e);
        }

        // If we failed to load any Lutemons, ensure we have the defaults
        if (allLutemons.isEmpty()) {
            allLutemons.add(new Red("scizer"));
            allLutemons.add(new Green("snivy"));
            allLutemons.add(new Pink("mew"));
        }
    }

    private static Lutemon createLutemonFromParts(String[] parts) {
        try {
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
            int totalTrainings = Integer.parseInt(parts[10]);

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
                lutemon.setAttack(atk);
                lutemon.setDefense(def);
                lutemon.setHealth(hp);
                lutemon.setExperience(exp);
                lutemon.setImageResourceRight(imageRight);
                lutemon.setImageResourceLeft(imageLeft);
                lutemon.setTotalBattles(totalBattles);
                lutemon.setWins(wins);
                lutemon.setTotalTrainings(totalTrainings);
            }

            return lutemon;
        } catch (Exception e) {
            Log.e(TAG, "Error creating Lutemon from parts", e);
            return null;
        }
    }
}
