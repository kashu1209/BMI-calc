package com.example.bmicalc;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String HEIGHT_INPUT = "height_input";
    private static final String WEIGHT_INPUT = "weight_input";
    private static final String FINAL_RESULT = "final_result";
    private static final String VISIBILITY_KEYPAD = "visibility_keypad";
    private static final String CATEGORY_WEIGHT = "categoryWeight";

    private static final Double CENTIMETER_CONVERSION_RATE = 0.01;
    private static final Double METER_CONVERSION_RATE = 1.00;
    private static final Double FEET_CONVERSION_RATE = 0.3048;
    private static final Double INCH_CONVERSION_RATE = 0.0254;

    private static final int INCH_POSITION = 3;
    private static final int FEET_POSITION = 2;
    private static final int METER_POSITION = 1;
    private static final int CENTIMETER_POSITION=0;

    private static final Double KILOGRAM_CONVERSION_RATE = 1.00;
    private static final Double POUND_CONVERSION_RATE = 2.205;

    private static final int KILOGRAM_POSITION=0;
    private static final int POUND_POSITION=1;

    private final String[] weightUnits = {"Kilogram", "Pound"};
    private final String[] heightUnits = {"Centimeter", "Meter", "Feet", "Inch"};

    private TextView weightUnit;
    private TextView heightUnit;
    private TextView output;
    private TextView valueHeight;
    private TextView valueWeight;
    private TextView categoryWeight;

    private View keypad;
    private View result;

    private double heightFactor = CENTIMETER_CONVERSION_RATE;
    private double weightFactor = KILOGRAM_CONVERSION_RATE;

    private String weightInput = "";
    private String heightInput = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner height_spinner = findViewById(R.id.spinner_height);
        Spinner weight_spinner = findViewById(R.id.spinner_weight);
        height_spinner.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                R.layout.itemfile, heightUnits));
        weight_spinner.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                R.layout.itemfile, weightUnits));

        heightUnit = findViewById(R.id.heightUnit);
        weightUnit = findViewById(R.id.weightUnit);

        valueHeight = findViewById(R.id.sampleHeight);
        valueWeight = findViewById(R.id.sampleWeight);

        categoryWeight = findViewById(R.id.category_weight);
        keypad = findViewById(R.id.calculator_container);
        result = findViewById(R.id.result_container);
        output = findViewById(R.id.output);

        if (savedInstanceState != null) {
            heightInput = savedInstanceState.getString(HEIGHT_INPUT);
            weightInput = savedInstanceState.getString(WEIGHT_INPUT);
            valueHeight.setText(heightInput);
            valueWeight.setText(weightInput);
            categoryWeight.setText(savedInstanceState.getString(CATEGORY_WEIGHT));
            if (categoryWeight.getText().toString().equals("Normal")) {
                categoryWeight.setTextColor(getResources().getColor(R.color.green));
            } else if (categoryWeight.getText().toString().equals("Overweight")) {
                categoryWeight.setTextColor(getResources().getColor(R.color.darker_orange));
            } else {
                categoryWeight.setTextColor(getResources().getColor(R.color.Dark_orange));
            }

            output.setText(savedInstanceState.getString(FINAL_RESULT));
            keypad.setVisibility(savedInstanceState.getInt(VISIBILITY_KEYPAD));
            if (savedInstanceState.getInt(VISIBILITY_KEYPAD) == View.VISIBLE) {
                result.setVisibility(View.GONE);
            } else {
                result.setVisibility(View.VISIBLE);
            }
        }

        findViewById(R.id.input_weight).setOnClickListener(this);
        findViewById(R.id.button0).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);
        findViewById(R.id.buttonAC).setOnClickListener(this);
        findViewById(R.id.buttonDEL).setOnClickListener(this);
        findViewById(R.id.button_dot).setOnClickListener(this);
        findViewById(R.id.buttonGO).setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(HEIGHT_INPUT, valueHeight.getText().toString());
        outState.putString(WEIGHT_INPUT, valueWeight.getText().toString());
        outState.putString(FINAL_RESULT, output.getText().toString());
        outState.putString(CATEGORY_WEIGHT, categoryWeight.getText().toString());

        outState.putInt(VISIBILITY_KEYPAD, (keypad.getVisibility()));
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.sampleWeight) {
            weightInput = "";
            keypad.setVisibility(View.VISIBLE);
            result.setVisibility(View.GONE);
            valueWeight.setTextColor(getResources().getColor(R.color.orange));
            valueHeight.setTextColor(getResources().getColor(R.color.grey));
        } else if (view.getId()==R.id.sampleHeight) {
            heightInput = "";
            keypad.setVisibility(View.VISIBLE);
            result.setVisibility(View.GONE);
            valueHeight.setTextColor(getResources().getColor(R.color.orange));
            valueWeight.setTextColor(getResources().getColor(R.color.grey));
        } else if (view.getId() == R.id.input_height) {
            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).setItems(heightUnits, (dialog1, i) -> {

                switch (i) {
                    case CENTIMETER_POSITION:
                        heightUnit.setText(heightUnits[CENTIMETER_POSITION]);
                        heightFactor = CENTIMETER_CONVERSION_RATE;
                        break;

                    case METER_POSITION:
                        heightUnit.setText(heightUnits[METER_POSITION]);
                        heightFactor = METER_CONVERSION_RATE;
                        break;
                    case FEET_POSITION:
                        heightUnit.setText(heightUnits[FEET_POSITION]);
                        heightFactor = FEET_CONVERSION_RATE;
                        break;
                    case INCH_POSITION:
                        heightUnit.setText(heightUnits[INCH_POSITION]);
                        heightFactor = INCH_CONVERSION_RATE;
                        break;
                }
                keypad.setVisibility(View.VISIBLE);
                result.setVisibility(View.GONE);
                valueHeight.setTextColor(getResources().getColor(R.color.orange));
                valueWeight.setTextColor(getResources().getColor(R.color.grey));
            }).create();
            dialog.show();
        } else if (view.getId() == R.id.input_weight) {
            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).setItems(weightUnits, (dialog1, i) -> {

                switch (i) {
                    case KILOGRAM_POSITION:
                        weightUnit.setText(weightUnits[KILOGRAM_POSITION]);
                        weightFactor = KILOGRAM_CONVERSION_RATE;
                        break;

                    case POUND_POSITION:
                        weightUnit.setText(weightUnits[POUND_POSITION]);
                        weightFactor = POUND_CONVERSION_RATE;
                        break;
                }
                keypad.setVisibility(View.VISIBLE);
                result.setVisibility(View.GONE);
                valueWeight.setTextColor(getResources().getColor(R.color.orange));
                valueHeight.setTextColor(getResources().getColor(R.color.green));
            }).create();
            dialog.show();
        } else if (view.getId() == R.id.button_dot) {
            if (getResources().getColor(R.color.orange) == valueHeight.getCurrentTextColor()) {
                if (!heightInput.contains(".")) {
                    heightInput = valueHeight.getText().toString();
                    heightInput += ".";
                    valueHeight.setText(heightInput);
                }
            } else {
                if (!weightInput.contains(".")) {
                    weightInput = valueWeight.getText().toString();
                    weightInput += ".";
                    valueWeight.setText(weightInput);
                }
            }
        } else if (view.getId()==R.id.buttonAC) {
            if (getResources().getColor(R.color.orange) == valueHeight.getCurrentTextColor()) {
                heightInput = "0";
                valueHeight.setText(heightInput);
            } else if (Color.parseColor("#ff9900") == valueWeight.getCurrentTextColor()) {
                weightInput = "0";
                valueWeight.setText(weightInput);
            }
        } else if (view.getId()==R.id.buttonGO) {
            float height = Float.parseFloat(valueHeight.getText().toString()) * (float) heightFactor;
            float weight = Float.parseFloat(valueWeight.getText().toString()) / (float) weightFactor;
            if (height == 0 || weight == 0) {
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.invalid_input),
                        Toast.LENGTH_LONG).show();
            } else {
                float out = weight / (height * height);
                output.setText(String.valueOf(out));
                if (Float.parseFloat(output.getText().toString()) <= 18.5) {
                    categoryWeight.setTextColor(getResources().getColor(R.color.Dark_orange));
                    categoryWeight.setText(getResources().getString(R.string.underweight));
                } else if (Float.parseFloat(output.getText().toString()) <= 25.0) {
                    categoryWeight.setText(getResources().getString(R.string.normal));
                    categoryWeight.setTextColor(getResources().getColor(R.color.green));
                } else if (Float.parseFloat(output.getText().toString()) > 50) {
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.invalid_input),
                            Toast.LENGTH_LONG).show();
                    return;
                } else {
                    categoryWeight.setText(getResources().getString(R.string.over_weight));
                    categoryWeight.setTextColor(getResources().getColor(R.color.darker_orange));
                }
                keypad.setVisibility(View.GONE);
                result.setVisibility(View.VISIBLE);
            }
        } else if (view.getId()==R.id.buttonDEL) {
            if (getResources().getColor(R.color.orange) == valueHeight.getCurrentTextColor()) {
                heightInput = valueHeight.getText().toString();
                if (heightInput.length() == 1) {
                    heightInput = "";
                    heightInput += "0";
                    valueHeight.setText(heightInput);
                } else {
                    StringBuilder builder = new StringBuilder(heightInput);
                    builder.deleteCharAt(heightInput.length() - 1);
                    heightInput = "";
                    heightInput += builder;
                    valueHeight.setText(heightInput);
                }
            } else {
                weightInput = valueWeight.getText().toString();
                if (weightInput.length() == 1) {
                    weightInput = "";
                    weightInput += "0";
                    valueWeight.setText(weightInput);
                } else {
                    StringBuilder builder = new StringBuilder(weightInput);
                    builder.deleteCharAt(weightInput.length() - 1);
                    weightInput = "";
                    weightInput += builder;
                    valueWeight.setText(weightInput);
                }
            }
        } else {
            if (Color.parseColor("#ff9900") == valueHeight.getCurrentTextColor()) {
                if (heightInput.equals("0"))
                    heightInput = "";
                if (heightInput.contains(".")) {
                    if (heightInput.length() < (heightInput.indexOf('.') + 3)) {
                        heightInput += ((Button) view).getText();
                        valueHeight.setText(heightInput);
                    }
                } else {
                    if (heightInput.length() < 3) {
                        heightInput += ((Button) view).getText();
                        valueHeight.setText(heightInput);
                    }
                }
            } else if (Color.parseColor("#ff9900") == valueWeight.getCurrentTextColor()) {
                if (weightInput.equals("0"))
                    weightInput = "";
                if (weightInput.contains(".")) {
                    if (weightInput.length() < (weightInput.indexOf('.') + 3)) {
                        weightInput += ((Button) view).getText();
                        valueWeight.setText(weightInput);
                    }
                } else {
                    if (weightInput.length() < 3) {
                        weightInput += ((Button) view).getText();
                        valueWeight.setText(weightInput);
                    }
                }
            }
        }
    }
}