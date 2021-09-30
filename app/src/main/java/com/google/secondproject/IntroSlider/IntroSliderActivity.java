package com.google.secondproject.IntroSlider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.secondproject.R;

public class IntroSliderActivity extends AppCompatActivity {

    SliderPrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_slider);

        prefManager = new SliderPrefManager(this);
        if (prefManager.getSkipSlider()) {
            prefManager.setSkipSlider(false);
            Toast.makeText(this, "skip", Toast.LENGTH_SHORT).show();
        } else if (prefManager.getStartSlider()) {
            Intent intent = new Intent(IntroSliderActivity.this, SlideShowV1.class);
            startActivity(intent);
            finish();
        }

        findViewById(R.id.btn_slide_show_v1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefManager.setStartSlider(true);
                startActivity(new Intent(IntroSliderActivity.this, SlideShowV1.class));
                finish();
            }
        });

        findViewById(R.id.btn_slide_show_v2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefManager.setStartSlider(true);
                startActivity(new Intent(IntroSliderActivity.this, SlideShowV2.class));
                finish();
            }
        });
    }
}