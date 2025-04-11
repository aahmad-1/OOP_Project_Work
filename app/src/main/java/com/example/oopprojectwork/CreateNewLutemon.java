package com.example.oopprojectwork;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
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

        // Set the default image based on the default selected radio button
        int defaultSelectedId = colorGroup.getCheckedRadioButtonId();
        if (defaultSelectedId != -1) {
            updatePreviewImage(defaultSelectedId);
        } else {
            // Pre-select the first radio button if none is selected
            RadioButton firstRadioButton = (RadioButton) colorGroup.getChildAt(0);
            if (firstRadioButton != null) {
                firstRadioButton.setChecked(true);
            }
        }

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