package com.example.oopprojectwork.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.oopprojectwork.Lutemon.Black;
import com.example.oopprojectwork.Lutemon.Green;
import com.example.oopprojectwork.Lutemon.Lutemon;
import com.example.oopprojectwork.Lutemon.Orange;
import com.example.oopprojectwork.Lutemon.Pink;
import com.example.oopprojectwork.Lutemon.Red;
import com.example.oopprojectwork.LutemonStorage;
import com.example.oopprojectwork.R;

public class CreateNewLutemon extends AppCompatActivity {

    private ImageView lutemonPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.create_new_lutemon);

        EditText editName = findViewById(R.id.editLutemonName);
        RadioGroup colorGroup = findViewById(R.id.radioColorGroup);
        Button createButton = findViewById(R.id.btnCreate);
        Button cancelButton = findViewById(R.id.btnCancel);
        lutemonPreview = findViewById(R.id.lutemonPreview);

        // Set up the radio button listener to update the preview image
        colorGroup.setOnCheckedChangeListener((group, checkedId) -> updatePreviewImage(checkedId));

        // We removed the auto-selection of the first radio button as requested

        createButton.setOnClickListener(v -> {
            String name = editName.getText().toString().trim();
            int selectedId = colorGroup.getCheckedRadioButtonId();

            // Validate inputs
            if (selectedId == -1) {
                Toast.makeText(this, "Please select a Lutemon first", Toast.LENGTH_SHORT).show();
                return;
            }

            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter a name for your Lutemon first", Toast.LENGTH_SHORT).show();
                return;
            }

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
            LutemonStorage.saveToFile(this);
            Toast.makeText(this, "Lutemon created!", Toast.LENGTH_SHORT).show();

            // Navigate to Home screen instead of just finishing
            Intent intent = new Intent(CreateNewLutemon.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });

        cancelButton.setOnClickListener(v -> finish());
    }

    private void updatePreviewImage(int selectedRadioButtonId) {
        // Set the appropriate image based on the selected radio button
        if (selectedRadioButtonId == R.id.radioRed) {
            lutemonPreview.setImageResource(R.drawable.scizer_facing_right);
        } else if (selectedRadioButtonId == R.id.radioGreen) {
            lutemonPreview.setImageResource(R.drawable.snivy_facing_right);
        } else if (selectedRadioButtonId == R.id.radioPink) {
            lutemonPreview.setImageResource(R.drawable.mew_facing_right);
        } else if (selectedRadioButtonId == R.id.radioOrange) {
            lutemonPreview.setImageResource(R.drawable.charmander_menu_screen);
        } else if (selectedRadioButtonId == R.id.radioBlack) {
            lutemonPreview.setImageResource(R.drawable.umbreon_facing_right);
        }
    }
}