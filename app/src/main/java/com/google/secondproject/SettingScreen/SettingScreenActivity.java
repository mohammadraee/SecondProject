package com.google.secondproject.SettingScreen;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.google.secondproject.R;


public class SettingScreenActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_screen);
    }
}