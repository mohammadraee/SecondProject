package com.google.secondproject.DataBase;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.secondproject.JsonXml.CustView.FlowerAdapter;
import com.google.secondproject.JsonXml.Element.Flower;
import com.google.secondproject.JsonXml.Json.JsonParser;
import com.google.secondproject.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DataBaseActivity extends AppCompatActivity {

    FlowerDBHelper dbHelper;
    Button btnInsert, btnLoad, btnAsc, btnDesc;
    List<Flower> flowerList = new ArrayList<>();
    ListView dbListView;
    SearchView dbSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        dbHelper = new FlowerDBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db != null && db.isOpen()) {
            Log.i(dbHelper.LOGTAG, "database opened.");
            db.close();
            Log.i(dbHelper.LOGTAG, "database closed.");
        }

        btnInsert = findViewById(R.id.btn_insert_db);
        btnLoad = findViewById(R.id.btn_load_db);
        btnAsc = findViewById(R.id.btn_asc_db);
        btnDesc = findViewById(R.id.btn_desc_db);
        dbListView = findViewById(R.id.db_listview);
        dbSearchView = findViewById(R.id.db_search_view);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputStream inputStream = getResources().openRawResource(R.raw.flowers_json);
                flowerList = new JsonParser().parseJson(inputStream);
                for (Flower flower : flowerList) {
                    dbHelper.insert(flower);
                }
                Log.i(dbHelper.LOGTAG, flowerList.size() + " rows was inserted into DB");
            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flowerList = dbHelper.readAllFlower();
                refreshDisplay();
            }
        });

        btnAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flowerList = dbHelper.getFlowers(null,null,Flower.KEY_PRICE + " ASC");
                refreshDisplay();
            }
        });

        btnDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flowerList = dbHelper.getFlowers(null,null,Flower.KEY_PRICE + " DESC");
                refreshDisplay();
            }
        });

        dbSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                flowerList = dbHelper.getFlowers(Flower.KEY_NAME + " LIKE '%" + newText + "%'", null);
                refreshDisplay();
                return false;
            }
        });

    }

    private void refreshDisplay() {
        FlowerAdapter adapter = new FlowerAdapter(DataBaseActivity.this, flowerList);
        dbListView.setAdapter(adapter);
    }
}