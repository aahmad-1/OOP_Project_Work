package com.example.oopprojectwork.Lutemon;
import com.example.oopprojectwork.R;
public class Orange extends Lutemon {
    public Orange(String name) {
        super(name, 8, 1, 17, "orange", R.drawable.charmander_menu_screen, R.drawable.charmander_facing_left);
    }
    @Override
    public String color() {
        return "Orange";
    }
}
