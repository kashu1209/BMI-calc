package com.example.bmicalc;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String KILOGRAM = "Kilograms";
    private static final String POUND = "Pounds";
    private static final String CENTIMETER = "Centimeter";
    private static final String METER = "Meter";
    private static final String FEET = "Feet";
    private static final String INCH = "Inch";
    private static final String HEIGHT_INPUT = "height_input";
    private static final String WEIGHT_INPUT = "weight_input";
    private static final String FINAL_RESULT = "final_result";
    private static final String VISIBLITY_CALCULATOR = "visiblity_calculator";
    private static final String CATEGORY_WEIGHT = "category_weight";
    String[] weightUnits = {KILOGRAM, POUND};
    String[] heightUnits = {CENTIMETER, METER, FEET, INCH};
    Spinner height_spinner;
    Spinner weight_spinner;
    TextView weight_unit;
    TextView height_unit;
    TextView output;
    ArrayAdapter<String> adapterWeight;
    ArrayAdapter<String> adapterHeight;
    TextView sample_height;
    double height_factor=1;
    double weight_factor=1;
    TextView sample_weight;
    String weight_in = "";
    String height_in = "";
    Button zero, one, two, three, four, five, six, seven, eight, nine, dot, go, ac;
    ImageButton del;
    View calculator;
    View result;
    TextView category_weight;
    float out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        height_spinner = (Spinner) findViewById(R.id.spinner_height);
        weight_spinner = (Spinner) findViewById(R.id.spinner_weight);
        height_unit = (TextView) findViewById(R.id.heightUnit);
        weight_unit = (TextView) findViewById(R.id.weightUnit);
        adapterHeight = new ArrayAdapter<String>(getApplicationContext(), R.layout.itemfile, heightUnits);
        height_spinner.setAdapter(adapterHeight);
        adapterWeight = new ArrayAdapter<String>(getApplicationContext(), R.layout.itemfile, weightUnits);
        weight_spinner.setAdapter(adapterWeight);
        sample_height = (TextView) findViewById(R.id.sampleHeight);
        sample_weight = (TextView) findViewById(R.id.sampleWeight);
        zero = (Button) findViewById(R.id.button0);
        one = (Button) findViewById(R.id.button1);
        two = (Button) findViewById(R.id.button2);
        three = (Button) findViewById(R.id.button3);
        four = (Button) findViewById(R.id.button4);
        five = (Button) findViewById(R.id.button5);
        six = (Button) findViewById(R.id.button6);
        seven = (Button) findViewById(R.id.button7);
        eight = (Button) findViewById(R.id.button8);
        nine = (Button) findViewById(R.id.button9);
        dot = (Button) findViewById(R.id.buttondot);
        go = (Button) findViewById(R.id.buttonGO);
        ac = (Button) findViewById(R.id.buttonAC);
        del = (ImageButton) findViewById(R.id.buttonDEL);
        category_weight = (TextView) findViewById(R.id.category_weight);
        calculator = findViewById(R.id.calculator_container);
        result = findViewById(R.id.result_container);
        output = findViewById(R.id.output);

        if (savedInstanceState != null) {
            height_in = savedInstanceState.getString(HEIGHT_INPUT);
            weight_in = savedInstanceState.getString(WEIGHT_INPUT);
            sample_height.setText(height_in);
            sample_weight.setText(weight_in);
            category_weight.setText(savedInstanceState.getString(CATEGORY_WEIGHT));
            if (category_weight.getText().toString().equals("Normal")) {
                category_weight.setTextColor(getResources().getColor(R.color.green));
            }
            else if (category_weight.getText().toString().equals("Overweight")) {
                category_weight.setTextColor(getResources().getColor(R.color.darker_orange));
            }
            else {
                category_weight.setTextColor(getResources().getColor(R.color.Dark_orange));
            }
            int Visibility_calculator;
            String result_bmi;
            result_bmi = savedInstanceState.getString(FINAL_RESULT);
            output.setText(result_bmi);


            Visibility_calculator = savedInstanceState.getInt(VISIBLITY_CALCULATOR);
            calculator.setVisibility(Visibility_calculator);
            if (Visibility_calculator == View.VISIBLE) {
                result.setVisibility(View.GONE);
            } else {
                result.setVisibility(View.VISIBLE);
            }

        }

        sample_height.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calculator.setVisibility(View.VISIBLE);
                result.setVisibility(View.GONE);
                sample_height.setTextColor(Color.parseColor("#ff9900"));
                sample_weight.setTextColor(Color.parseColor("#919191"));
            }
        });
        sample_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator.setVisibility(View.VISIBLE);
                result.setVisibility(View.GONE);
                sample_weight.setTextColor(Color.parseColor("#ff9900"));
                sample_height.setTextColor(Color.parseColor("#919191"));
            }
        });

        ArrayList<Button> list = new ArrayList<Button>();
        list.add(zero);
        list.add(one);
        list.add(two);
        list.add(three);
        list.add(four);
        list.add(five);
        list.add(six);
        list.add(seven);
        list.add(eight);
        list.add(nine);

        for (int i = 0; i < list.size(); i++) {
            list.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Color.parseColor("#ff9900") == sample_height.getCurrentTextColor()) {
                        if(height_in.equals("0"))
                            height_in="";
                        if (height_in.contains(".")) {
                            if (height_in.length() < (height_in.indexOf('.') + 3)) {
                                height_in += ((Button) view).getText();
                                sample_height.setText(height_in);
                            }
                        } else {
                            if (height_in.length() < 3) {
                                height_in += ((Button) view).getText();
                                sample_height.setText(height_in);
                            }
                        }
                    } else if(Color.parseColor("#ff9900") == sample_weight.getCurrentTextColor()) {
                        if(weight_in.equals("0"))
                            weight_in="";
                        if (weight_in.contains(".")) {
                            if (weight_in.length() < (weight_in.indexOf('.') + 3)) {
                                weight_in += ((Button) view).getText();
                                sample_weight.setText(weight_in);
                            }
                        } else {
                            if (weight_in.length() < 3) {
                                weight_in += ((Button) view).getText();
                                sample_weight.setText(weight_in);
                            }
                        }
                    }
                }
            });
        }
        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Color.parseColor("#ff9900") == sample_height.getCurrentTextColor()) {
                    height_in = "0";
                    sample_height.setText(height_in);
                } else if (Color.parseColor("#ff9900") == sample_weight.getCurrentTextColor()) {
                    weight_in = "0";
                    sample_weight.setText(weight_in);
                }
            }
        });

        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Color.parseColor("#ff9900") == sample_height.getCurrentTextColor()) {
                    if(!height_in.contains(".")){
                        height_in = sample_height.getText().toString();
                        height_in+=".";
                        sample_height.setText(height_in);
                    }
                } else {
                    if(!weight_in.contains(".")){
                        weight_in = sample_weight.getText().toString();
                        weight_in+=".";
                        sample_weight.setText(weight_in);
                    }
                }

            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float a = Float.parseFloat(sample_height.getText().toString()) * (float) height_factor;
                float b = Float.parseFloat(sample_weight.getText().toString()) / (float) weight_factor;
                if (a == 0 || b == 0) {
                    Toast.makeText(getApplicationContext(), "Enter correct information",
                            Toast.LENGTH_LONG).show();
                } else {
                    out = b / (a * a);
                    output.setText(Float.toString(out));
                    if (Float.parseFloat(output.getText().toString()) <= 18.5) {
                        category_weight.setTextColor(getResources().getColor(R.color.Dark_orange));
                        category_weight.setText("Underweight");
                    } else if (Float.parseFloat(output.getText().toString()) <= 25.0) {
                        category_weight.setText("Normal");
                        category_weight.setTextColor(getResources().getColor(R.color.green));
                    } else if (Float.parseFloat(output.getText().toString()) >= 50) {
                        Toast.makeText(getApplicationContext(), "Invalid BMI! Please check your input",
                                Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        category_weight.setText("Overweight");
                        category_weight.setTextColor(getResources().getColor(R.color.darker_orange));
                    }
                calculator.setVisibility(View.GONE);
                result.setVisibility(View.VISIBLE);
                }
            }
        });


        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getResources().getColor(R.color.orange) == sample_height.getCurrentTextColor()) {
                    height_in = sample_height.getText().toString();
                    if (height_in.length() == 1) {
                        height_in = "";
                        height_in += "0";
                        sample_height.setText(height_in);
                    } else {
                        StringBuilder builder = new StringBuilder(height_in);
                        builder.deleteCharAt(height_in.length() - 1);
                        height_in = "";
                        height_in += builder;
                        sample_height.setText(height_in);
                    }
                } else {
                    weight_in = sample_weight.getText().toString();
                    if (weight_in.length() == 1) {
                        weight_in = "";
                        weight_in += "0";
                        sample_weight.setText(weight_in);
                    } else {
                        StringBuilder builder = new StringBuilder(weight_in);
                        builder.deleteCharAt(weight_in.length() - 1);
                        weight_in = "";
                        weight_in += builder;
                        sample_weight.setText(weight_in);
                    }
                }
            }
        });
        height_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        height_unit.setText("" + heightUnits[i]);
                        height_factor = 0.01;
                        break;

                    case 1:
                        height_unit.setText("" + heightUnits[i]);
                        height_factor = 1;
                        break;
                    case 2:
                        height_unit.setText("" + heightUnits[i]);
                        height_factor = 0.3048;
                        break;
                    case 3:
                        height_unit.setText("" + heightUnits[i]);
                        height_factor = 0.0254;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        weight_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        weight_unit.setText("" + weightUnits[i]);
                        weight_factor = 1;
                        break;

                    case 1:
                        weight_unit.setText("" + weightUnits[i]);
                        weight_factor = 2.205;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });




    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(HEIGHT_INPUT, sample_height.getText().toString());
        outState.putString(WEIGHT_INPUT, sample_weight.getText().toString());
        outState.putString(FINAL_RESULT, output.getText().toString());
        outState.putString(CATEGORY_WEIGHT,category_weight.getText().toString());

        outState.putInt(VISIBLITY_CALCULATOR, (calculator.getVisibility()));
    }

}
