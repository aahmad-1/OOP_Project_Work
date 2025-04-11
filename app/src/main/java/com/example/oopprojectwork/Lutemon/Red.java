package com.example.oopprojectwork.Lutemon;
import com.example.oopprojectwork.R;

public class Red extends Lutemon {
    public Red(String name) {
        super(name, 5, 4, 20, "red", R.drawable.scizer_facing_right);
    }

    @Override
    public String color() {
        return "Red";
    }
}
