package com.example.tp1;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tp1.R;

public class MainActivity extends AppCompatActivity {
    private EditText inputNumber;
    private Spinner fromBaseSpinner, toBaseSpinner;

    private Button convertButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNumber = findViewById(R.id.inputNumber);
        fromBaseSpinner = findViewById(R.id.fromBaseSpinner);
        toBaseSpinner = findViewById(R.id.toBaseSpinner);
        convertButton = findViewById(R.id.convertButton);
        resultText = findViewById(R.id.resultText);

        String[] bases = {"Décimal", "Binaire", "Octal", "Hexadécimal"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, bases);
        fromBaseSpinner.setAdapter(adapter);
        toBaseSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(view -> convertir());
    }

    private void convertir() {
        String input = inputNumber.getText().toString();
        int fromBase = getBase(fromBaseSpinner.getSelectedItem().toString());
        int toBase = getBase(toBaseSpinner.getSelectedItem().toString());

        try {
            int decimalValue = Integer.parseInt(input, fromBase);
            String result = Integer.toString(decimalValue, toBase).toUpperCase();
            resultText.setText("Résultat: " + result);
        } catch (NumberFormatException e) {
            resultText.setText("Erreur de conversion");
        }
    }

    private int getBase(String base) {
        switch (base) {
            case "Binaire": return 2;
            case "Octal": return 8;
            case "Hexadécimal": return 16;
            default: return 10;
        }
    }
}
