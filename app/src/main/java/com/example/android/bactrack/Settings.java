package com.example.android.bactrack;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {

    //weight in kilograms or pounds
    public static boolean pounds = false;
    EditText userWeightET;
    //weight
    String strWeight;
    public double userWeight;
    double distributionRatio;
    RadioButton radioMale;
    RadioButton radioButton0;
    TextView weightType;
    RadioGroup BodyType;
    RadioGroup gender;

    public static final String SHARED_PREFS_SETTINGS = "sharedPrefsSettings";
    public static final String WEIGHT = "userWeight";
    public static final String GENDER = "gender";
    public static final String BODYTYPE = "bodytype";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        radioButton0 = findViewById(R.id.radioButton0);
        userWeightET = findViewById(R.id.userWeight);
        radioMale = findViewById(R.id.radioMale);
        BodyType = findViewById(R.id.BodyType);
        weightType = findViewById(R.id.weightType);
        Button changeWeight = findViewById(R.id.changeWeight);
        Button submit = findViewById(R.id.submit);
        gender = findViewById(R.id.gender);
        BodyType = findViewById(R.id.BodyType);

        //initialize RadioGroups
        gender.check(R.id.radioMale);
        BodyType.check(R.id.radioButton0);

        //load saved data if Settings is opened within MainAdd
        Bundle settings = getIntent().getExtras();
        if (settings != null) {
            loadData();
        }


        //set imperial/metric
        changeWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imperialToMetric();
            }
        });

        //submit settings to main activity
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strWeight = userWeightET.getText().toString();
                if (TextUtils.isEmpty(strWeight)) {
                    Toast.makeText(Settings.this, "Please enter weight", Toast.LENGTH_SHORT).show();
                } else {
                    saveData();

                    userWeight = Double.parseDouble(strWeight);
                    Intent intent = new Intent(Settings.this, MainAdd.class);
                    intent.putExtra("distributionRatio", radioButtons());
                    intent.putExtra("userWeight", userWeightKGPD());

                    startActivity(intent);
                }
            }
        });
    }

    public void imperialToMetric() {
        if (pounds) {
            ((TextView) findViewById(R.id.weightType)).setText(R.string.kilograms);
            ((TextView) findViewById(R.id.changeWeight)).setText(R.string.set_to_imperial);
        } else { // currently accepting kgs
            ((TextView) findViewById(R.id.weightType)).setText(R.string.pounds);
            ((TextView) findViewById(R.id.changeWeight)).setText(R.string.set_to_metric);
        }
        pounds = !pounds;
    }

    public double userWeightKGPD() {
        //checks weight (kg/pd)
        if (pounds)
            userWeight *= 0.45;
        return userWeight;
    }

    public double radioButtons() {
        if (radioMale.isChecked())
            distributionRatio = 0.68;
        else distributionRatio = 0.55;
        if (BodyType.getCheckedRadioButtonId() == findViewById(R.id.radioButtonNeg2).getId())
            distributionRatio -= 0.1;
        if (BodyType.getCheckedRadioButtonId() == findViewById(R.id.radioButtonNeg1).getId())
            distributionRatio -= 0.05;
        if (BodyType.getCheckedRadioButtonId() == findViewById(R.id.radioButton1).getId())
            distributionRatio += 0.05;
        if (BodyType.getCheckedRadioButtonId() == findViewById(R.id.radioButton2).getId())
            distributionRatio += 0.1;
        return distributionRatio;
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(WEIGHT, strWeight);
        editor.putInt(GENDER, gender.getCheckedRadioButtonId());
        editor.putInt(BODYTYPE, BodyType.getCheckedRadioButtonId());

        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_SETTINGS, Context.MODE_PRIVATE);
        int savedRadioIndex1 = sharedPreferences.getInt(GENDER, 0);
        int savedRadioIndex2 = sharedPreferences.getInt(BODYTYPE, 0);

        userWeightET.setText(sharedPreferences.getString(WEIGHT, null));
        gender.check(savedRadioIndex1);
        BodyType.check(savedRadioIndex2);

    }
}
