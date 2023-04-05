package com.example.lentecontato;


import static com.google.android.material.internal.ContextUtils.getActivity;
import static java.lang.Integer.parseInt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;


public class MainScreen extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    private EditText leftUses;
    private EditText rightUses;

    private EditText date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);


        leftUses = (EditText) findViewById(R.id.leftUses);
        rightUses = (EditText) findViewById(R.id.rightUses);
        date = (EditText) findViewById(R.id.editTextDate);

        recoverSides();

        Button leftIncrease = (Button) findViewById(R.id.increaseLeft);
        Button leftDecrease = (Button) findViewById(R.id.decreaseLeft);
        Button rightDecrease = (Button) findViewById(R.id.decreaseRight);
        Button rightIncrease = (Button) findViewById(R.id.increaseRight);

        //Button saveButton = findViewById(R.id.saveButton);

        date.addTextChangedListener(MaskEditUtil.mask(date,MaskEditUtil.FORMAT_DATE));

        leftIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer qnt = parseInt(leftUses.getText().toString());
                qnt+=1;
                leftUses.setText(qnt.toString());
                leftUses.invalidate();
            }
        });
        leftDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer qnt = parseInt(leftUses.getText().toString());
                if(qnt>0) qnt-=1;
                leftUses.setText(qnt.toString());
                leftUses.invalidate();
            }
        });
        rightIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer qnt = parseInt(rightUses.getText().toString());
                qnt+=1;
                rightUses.setText(qnt.toString());
                rightUses.invalidate();
            }
        });
        rightDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer qnt = parseInt(rightUses.getText().toString());
                if(qnt>0) qnt-=1;
                rightUses.setText(qnt.toString());
                rightUses.invalidate();
            }
        });
        /*saveButton.setOnClickListener(v -> {
            saveData();
        });*/
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveData();
    }

    public void recoverSides(){
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        String qntLeft = sharedPref.getString("leftLen","");
        String qntRight = sharedPref.getString("rightLen","");
        String setDate = sharedPref.getString("date","");
        leftUses.setText(qntLeft);
        rightUses.setText(qntRight);
        date.setText(setDate);
    }
    public void saveData(){
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);;
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("leftLen",leftUses.getText().toString());
        editor.putString("rightLen",rightUses.getText().toString());
        editor.putString("date",date.getText().toString());
        editor.commit();
    }
}
