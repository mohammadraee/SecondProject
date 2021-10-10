package com.google.secondproject.XmlAnimation;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.secondproject.R;

public class XmlAnimActivity extends AppCompatActivity implements View.OnClickListener {

    androidx.appcompat.widget.AppCompatSpinner spinnerAnim;
    androidx.appcompat.widget.AppCompatButton btnStart;
    androidx.appcompat.widget.AppCompatImageView image;

    String [] animations = {"fade in", "fade out", "move", "rotate", "blink", "zoom in",
            "zoom out", "slide up", "slide down", "bounce", "sequential animation"
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_anim);
        spinnerAnim = findViewById(R.id.spinner_anim);
        btnStart = findViewById(R.id.btn_start);
        image = findViewById(R.id.imageview);
        setupSpinner();
        btnStart.setOnClickListener(this);
    }

    private void setupSpinner(){
        ArrayAdapter<String> spAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, animations);
        spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAnim.setAdapter(spAdapter);
    }


    @Override
    public void onClick(View view) {
        int index = spinnerAnim.getSelectedItemPosition();
        switch (index) {
            case 0 :
                fadeIn();
                break;
            case 1:
                fadeOut();
                break;
            case 2:
                move();
                break;
            case 3:
                rotate();
                break;
            case 4:
                blink();
                break;
            case 5 :
                zoomIn();
                break;
            case 6:
                zoomOut();
                break;
            case 7:
                slideUp();
                break;
            case 8 :
                slideDown();
                break;
            case 9:
                bounce();
                break;
            case 10:
                sequential();
                break;
            default:
                Toast.makeText(this, "Choose another animation", Toast.LENGTH_SHORT).show();
                break;
        }
    }




    private void fadeIn(){
        Animation animFadein = AnimationUtils.loadAnimation(this, R.anim.fadein);
        image.startAnimation(animFadein);
    }

    private void fadeOut(){
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.fadeout);

        image.startAnimation(anim);
    }

    private void move() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.move);
        image.startAnimation(anim);
    }

    private void rotate(){
        image.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate));
    }

    private void blink() {
        image.startAnimation(AnimationUtils.loadAnimation(this, R.anim.blink));
    }

    private void zoomIn(){
        image.startAnimation(AnimationUtils.loadAnimation(this, R.anim.zoomin));
    }

    private void zoomOut(){
        image.startAnimation(AnimationUtils.loadAnimation(this, R.anim.zoomout));
    }

    private void slideUp(){
        image.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slideup));

    }

    private void slideDown(){
        image.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slidedown));
    }

    private void bounce(){
        image.startAnimation(AnimationUtils.loadAnimation(this, R.anim.bounce));
    }


    private void sequential() {
        image.startAnimation(AnimationUtils.loadAnimation(this, R.anim.sequential));
    }
}
