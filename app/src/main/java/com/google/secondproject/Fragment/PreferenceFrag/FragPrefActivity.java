package com.google.secondproject.Fragment.PreferenceFrag;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.google.secondproject.R;

public class FragPrefActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pref_frag);
        getFragmentManager().beginTransaction().replace(R.id.frag_pref,new FragPrefs()).commit();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Toast.makeText(this, "country : "+prefs.getString("country",""), Toast.LENGTH_SHORT).show();
    }

    public static class FragPrefs extends PreferenceFragment{
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.frag_prefs);
        }
    }
}