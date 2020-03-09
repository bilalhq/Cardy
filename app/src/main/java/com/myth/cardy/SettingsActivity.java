package com.myth.cardy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;

import java.util.prefs.Preferences;

public class SettingsActivity extends AppCompatActivity {

    Button buttonSave;
    TextInputEditText editTextLength;
    TextInputEditText editTextCode;
    Chip cZong,cUfone,cJazz,cWarid,cTelenor,cCustom;
    ChipGroup chipGroup;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        chipGroup = findViewById(R.id.chipGroup);
        cZong = findViewById(R.id.chipZong);
        cWarid = findViewById(R.id.chipWarid);
        cTelenor = findViewById(R.id.chipTelenor);
        cJazz = findViewById(R.id.chipJazz);
        cUfone = findViewById(R.id.chipUfone);
        cCustom = findViewById(R.id.chipCustom);
        buttonSave = findViewById(R.id.buttonSave);
        editTextCode = findViewById(R.id.editTextCodePrefix);
        editTextLength = findViewById(R.id.editTextCodeLength);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        editTextCode.setText(sharedPreferences.getString("Code",""));
        editTextLength.setText(sharedPreferences.getString("Length",""));

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextCode.getText().toString().equals("") && editTextLength.getText().toString().equals("")){
                    editTextCode.setError("Cannot be empty");
                    editTextLength.setError("Cannot be empty");
                }
                else if(editTextCode.getText().toString().equals("")){
                    editTextCode.setError("Cannot be empty");
                }
                else if(editTextLength.getText().toString().equals("")){
                    editTextLength.setError("Cannot be empty");
                }
                else {
                    sEditor = sharedPreferences.edit();
                    sEditor.putString("Code", editTextCode.getText().toString());
                    sEditor.putString("Length", editTextLength.getText().toString());
                    sEditor.apply();
                    Toast.makeText(SettingsActivity.this,"Saved",Toast.LENGTH_SHORT).show();
                }
            }
        });

        cCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextCode.setEnabled(true);
                editTextLength.setEnabled(true);
                editTextLength.setText("");
                editTextCode.setText("");
            }
        });

        cJazz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextCode.setEnabled(false);
                editTextLength.setEnabled(false);
                editTextLength.setText("14");
                editTextCode.setText("123");
                cJazz.setActivated(true);
                cJazz.setChecked(true);
            }
        });

        cTelenor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextCode.setEnabled(false);
                editTextLength.setEnabled(false);
                editTextLength.setText("14");
                editTextCode.setText("555");
            }
        });

        cUfone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextCode.setEnabled(false);
                editTextLength.setEnabled(false);
                editTextLength.setText("14");
                editTextCode.setText("123");
            }
        });

        cZong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextCode.setEnabled(false);
                editTextLength.setEnabled(false);
                editTextLength.setText("14");
                editTextCode.setText("101");
            }
        });

        cWarid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextCode.setEnabled(false);
                editTextLength.setEnabled(false);
                editTextLength.setText("16");
                editTextCode.setText("161");
            }
        });

    }
}
