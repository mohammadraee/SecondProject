package com.google.secondproject.MultiTab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.google.secondproject.MultiTab.SimpleTabActivity;
import com.google.secondproject.R;

public class TabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
    }


    public void onclick(View v){
        Intent intent = new Intent(this, SimpleTabActivity.class);
        intent.putExtra("type", (String)  v.getTag());
        startActivity(intent);
    }

}
