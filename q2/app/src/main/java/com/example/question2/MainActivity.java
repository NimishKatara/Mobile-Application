package com.example.question2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String[] units = {"Feet", "Inches", "Centimeters", "Meters", "Yards"};
    Spinner fromUnitSpinner, toUnitSpinner;
    EditText inputValue;
    TextView resultText;
    Button convertButton, settingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fromUnitSpinner = findViewById(R.id.fromUnit);
        toUnitSpinner = findViewById(R.id.toUnit);
        inputValue = findViewById(R.id.inputValue);
        resultText = findViewById(R.id.resultText);
        convertButton = findViewById(R.id.convertButton);
        settingsBtn = findViewById(R.id.settingsButton);  // Make sure you have a button with this ID in XML

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, units);
        fromUnitSpinner.setAdapter(adapter);
        toUnitSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertUnits();
            }
        });

        settingsBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        });
    }

    private void convertUnits() {
        String fromUnit = fromUnitSpinner.getSelectedItem().toString();
        String toUnit = toUnitSpinner.getSelectedItem().toString();
        String inputStr = inputValue.getText().toString();

        if (inputStr.isEmpty()) {
            Toast.makeText(this, "Enter a value", Toast.LENGTH_SHORT).show();
            return;
        }

        double input = Double.parseDouble(inputStr);
        double meters = toMeters(input, fromUnit);
        double result = fromMeters(meters, toUnit);

        resultText.setText(String.format("%.4f %s", result, toUnit));
    }

    private double toMeters(double value, String unit) {
        switch (unit) {
            case "Feet": return value * 0.3048;
            case "Inches": return value * 0.0254;
            case "Centimeters": return value / 100;
            case "Meters": return value;
            case "Yards": return value * 0.9144;
            default: return value;
        }
    }

    private double fromMeters(double meters, String unit) {
        switch (unit) {
            case "Feet": return meters / 0.3048;
            case "Inches": return meters / 0.0254;
            case "Centimeters": return meters * 100;
            case "Meters": return meters;
            case "Yards": return meters / 0.9144;
            default: return meters;
        }
    }
}
