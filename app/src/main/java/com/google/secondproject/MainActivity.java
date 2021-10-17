package com.google.secondproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.secondproject.DataBase.DataBaseActivity;
import com.google.secondproject.File.FileActivity;
import com.google.secondproject.Fragment.FragmentActivity;
import com.google.secondproject.IntroSlider.IntroSliderActivity;
import com.google.secondproject.JsonXml.JsonXmlActivity;
import com.google.secondproject.MultiTab.TabActivity;
import com.google.secondproject.Preference.PrefActivity;
import com.google.secondproject.RecyclerView.RecyclerViewActivity;
import com.google.secondproject.SettingScreen.SettingScreenActivity;
import com.google.secondproject.XmlAnimation.XmlAnimActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> topics;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareData();
        refreshDisplay();
    }

    private void prepareData() {
        topics = new ArrayList<>();
        topics.add("preferences");
        topics.add("setting screen");
        topics.add("file");
        topics.add("json xml");
        topics.add("intro slider");
        topics.add("data base");
        topics.add("fragment");
        topics.add("recycler view");
        topics.add("multi tab");
        topics.add("xml animation");
        topics.add("sharing card");
    }

    private void refreshDisplay() {
        listView = findViewById(R.id.course_topic_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, topics);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (topics.get(position)) {
                    case "preferences":
                        startActivity(new Intent(MainActivity.this, PrefActivity.class));
                        break;
                    case "setting screen":
                        startActivity(new Intent(MainActivity.this, SettingScreenActivity.class));
                        break;
                    case "file":
                        startActivity(new Intent(MainActivity.this, FileActivity.class));
                        break;
                    case "json xml":
                        startActivity(new Intent(MainActivity.this, JsonXmlActivity.class));
                        break;
                    case "intro slider":
                        startActivity(new Intent(MainActivity.this, IntroSliderActivity.class));
                        break;
                    case "data base":
                        startActivity(new Intent(MainActivity.this, DataBaseActivity.class));
                        break;
                    case "fragment":
                        startActivity(new Intent(MainActivity.this, FragmentActivity.class));
                        break;
                    case "recycler view":
                        startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
                        break;
                    case "multi tab":
                        startActivity(new Intent(MainActivity.this, TabActivity.class));
                        break;
                    case "xml animation":
                        startActivity(new Intent(MainActivity.this, XmlAnimActivity.class));
                        break;
                    case "sharing card":
                        startActivity(new Intent(MainActivity.this, SharingCardActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("info").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ShowSettingScreenInfo();

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void ShowSettingScreenInfo() {
        SharedPreferences appPref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        Boolean notification = appPref.getBoolean("notification", false);
        Boolean imageView = appPref.getBoolean("imageView", true);
        String name = appPref.getString("name", "");
        String bio = appPref.getString("bio", "");
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Setting Screen Info")
                .setMessage("name : " + name + "\n" +
                        "bio : " + bio + "\n" +
                        "imageView : " + imageView + "\n" +
                        "notification : " + notification)
                .show();
    }
}