package com.example.oopprojectwork;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class CreateNewLutemon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.create_new_lutemon);

        EditText editName = findViewById(R.id.editLutemonName);
        RadioGroup colorGroup = findViewById(R.id.radioColorGroup);
        Button createButton = findViewById(R.id.btnCreate);

        createButton.setOnClickListener(v -> {
            String name = editName.getText().toString();
            int selectedId = colorGroup.getCheckedRadioButtonId();

            Lutemon newLutemon;

            if (selectedId == R.id.radioRed) {
                newLutemon = new Red(name);
            } else if (selectedId == R.id.radioGreen) {
                newLutemon = new Green(name);
            } else if (selectedId == R.id.radioPink) {
                newLutemon = new Pink(name);
            } else if (selectedId == R.id.radioOrange) {
                newLutemon = new Orange(name);
            } else {
                newLutemon = new Black(name);
            }

            LutemonStorage.allLutemons.add(newLutemon);
            Toast.makeText(this, "Lutemon created!", Toast.LENGTH_SHORT).show();
            finish(); // Go back to list
        });


    }

}
