package com.example.oopprojectwork.Lutemon;
import com.example.oopprojectwork.R;


import androidx.annotation.NonNull;

public class Black extends Lutemon {
    public Black(String name) {
        super(name, 5, 5, 12, "black", R.drawable.umbreon_facing_right, R.drawable.umbreon_facing_left);
    }

    @Override
    public String color() {
        return "Black";
    }


}
