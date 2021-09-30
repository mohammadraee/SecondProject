package com.google.secondproject.Preference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.secondproject.R;

public class PrefActivity extends AppCompatActivity {
    EditText input_firstname, input_lastname, input_age;
    Button btn_save, btn_load;

    SharedPreferences pref;
    SharedPreferences appPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

//        pref = getPreferences(MODE_PRIVATE);
        pref = getSharedPreferences("myprefs", MODE_PRIVATE);
        initViews();
        appPref = PreferenceManager.getDefaultSharedPreferences(this);
    }

    private void initViews() {
        input_firstname = (EditText) findViewById(R.id.input_firstname);
        input_lastname = (EditText) findViewById(R.id.input_lastName);
        input_age = (EditText) findViewById(R.id.input_age);
        btn_load = (Button) findViewById(R.id.btn_load);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname = input_firstname.getText().toString().trim();
                String lastname = input_lastname.getText().toString().trim();
                String ageStr = input_age.getText().toString();
                if (!firstname.isEmpty() && !lastname.isEmpty() && !ageStr.isEmpty()) {
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("firstname", firstname);
                    editor.putString("lastname", lastname);
                    editor.putInt("age", Integer.valueOf(ageStr));
                    Toast.makeText(PrefActivity.this, "saved", Toast.LENGTH_SHORT).show();
                    editor.apply();
                }
            }
        });

        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = pref.getString("firstname", "NOT-FOUND");
                String lname = pref.getString("lastname", "NOT-FOUND");
                int age = pref.getInt("age", -1);
                new AlertDialog.Builder(PrefActivity.this)
                        .setTitle("pref-values")
                        .setMessage("firstname :    " + fname + "\nlastname :    " + lname +
                                "\nage :    " + (age == -1 ? "NOT-FOUND" : age))
                        .show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("test sharing preference").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(PrefActivity.this, TestSharingPrefActivity.class);
                startActivity(intent);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}