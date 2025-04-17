package com.example.oopprojectwork.Lutemon;
import com.example.oopprojectwork.R;
public class Green extends Lutemon {
    public Green(String name) {
        super(name, 6, 3, 19, "green", R.drawable.snivy_facing_right, R.drawable.snivy_facing_left);
    }

    @Override
    public String color() {
        return "Green";
    }

}
