package com.cantrowitz.fastersharedpreferences.demo;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.cantrowitz.fastersharedpreferences.FasterSharedPreferences;

public class MainActivity extends AppCompatActivity {

    private final static int NUMBER_OF_NAMES = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        String[] names = generateNames();

        long individualRunTime = runTestForIndividualFiles(names);
        long fasterSPRunTime = runTestForFasterSP(names);

        System.out.println("+++ Individual=" + individualRunTime + " ms FasterSP=" + fasterSPRunTime + "ms");
        System.out.println("+++ chart \t" + individualRunTime + "\t" + fasterSPRunTime);

    }

    private long runTestForFasterSP(String[] names) {
        Context applicationContext = getApplicationContext();
        long start = SystemClock.elapsedRealtime();
        for (int i = 0; i < NUMBER_OF_NAMES; i++) {
            FasterSharedPreferences.get(applicationContext, names[i], MODE_PRIVATE);
        }
        return SystemClock.elapsedRealtime() - start;
    }

    private long runTestForIndividualFiles(String[] names) {
        Context applicationContext = getApplicationContext();
        long start = SystemClock.elapsedRealtime();
        for (int i = 0; i < NUMBER_OF_NAMES; i++) {
            applicationContext.getSharedPreferences(names[i], MODE_PRIVATE);
        }
        return SystemClock.elapsedRealtime() - start;
    }

    @NonNull
    private String[] generateNames() {
        String[] names = new String[NUMBER_OF_NAMES];
        for (int i = 0; i < NUMBER_OF_NAMES; i++) {
            names[i] = String.format("prefs_%d", i);
        }
        return names;
    }
}
