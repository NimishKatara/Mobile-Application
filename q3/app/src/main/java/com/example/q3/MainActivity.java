package com.example.q3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    String[] u = {"Feet", "Inches", "Centimeters", "Meters", "Yards"};
    Spinner from, to;
    EditText val;
    TextView res;
    Button btn;
    LottieAnimationView anim;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        from = findViewById(R.id.fromUnit);
        to = findViewById(R.id.toUnit);
        val = findViewById(R.id.inputValue);
        res = findViewById(R.id.resultText);
        btn = findViewById(R.id.convertButton);
        anim = findViewById(R.id.anim);

        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, u);
        from.setAdapter(ad);
        to.setAdapter(ad);

        btn.setOnClickListener(v -> convert());

        findViewById(R.id.settingsButton).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        });
    }

    void convert() {
        String f = from.getSelectedItem().toString();
        String t = to.getSelectedItem().toString();
        String s = val.getText().toString();

        if (s.isEmpty()) {
            Toast.makeText(this, "Enter a value", Toast.LENGTH_SHORT).show();
            return;
        }

        double input = Double.parseDouble(s);
        double m = toMeter(input, f);
        double out = fromMeter(m, t);

        res.setText(String.format("%.4f %s", out, t));
    }

    double toMeter(double x, String u) {
        switch (u) {
            case "Feet": return x * 0.3048;
            case "Inches": return x * 0.0254;
            case "Centimeters": return x / 100;
            case "Meters": return x;
            case "Yards": return x * 0.9144;
            default: return x;
        }
    }

    double fromMeter(double x, String u) {
        switch (u) {
            case "Feet": return x / 0.3048;
            case "Inches": return x / 0.0254;
            case "Centimeters": return x * 100;
            case "Meters": return x;
            case "Yards": return x / 0.9144;
            default: return x;
        }
    }
}
