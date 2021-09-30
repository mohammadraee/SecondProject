package com.google.secondproject.IntroSlider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.secondproject.R;

import org.jetbrains.annotations.NotNull;

public class SlideShowV1 extends AppCompatActivity {

    ViewPager viewPager;
    Button btnSkip, btnNext;
    LinearLayout dotsLayout;
    SliderAdapterV1 adapter;

    SliderPrefManager prefManager;

    int[] layoutIds = {R.layout.intro_slide_1, R.layout.intro_slide_2,
            R.layout.intro_slide_3, R.layout.intro_slide_4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slides);

        //remove ActionBar
        getSupportActionBar().hide();
        changeStatusBarColor();

        initViews();
        showDots(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                showDots(position);
                if (position == viewPager.getAdapter().getCount() - 1) {
                    btnNext.setText(R.string.gotit);
                    btnSkip.setVisibility(View.GONE);
                } else {
                    btnSkip.setVisibility(View.VISIBLE);
                    btnNext.setText(R.string.next);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currPage = viewPager.getCurrentItem();
                int lastPage = viewPager.getAdapter().getCount() - 1;
                if (currPage == lastPage) {
                    prefManager.setStartSlider(false);
                    launchActivityAfterSlideShow();
                } else {
                    viewPager.setCurrentItem(currPage + 1);
                }
            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefManager.setSkipSlider(true);
                launchActivityAfterSlideShow();
            }
        });
    }

    public void changeStatusBarColor(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

    }

    public void launchActivityAfterSlideShow() {
        startActivity(new Intent(SlideShowV1.this, IntroSliderActivity.class));
        finish();
    }

    public void showDots(int pageNumber) {
        TextView[] dots = new TextView[layoutIds.length];
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
            dots[i].setTextColor(ContextCompat.getColor(this, (i == pageNumber ? R.color.dot_active : R.color.dot_inactive)));
            dotsLayout.addView(dots[i]);
        }
    }

    private void initViews() {
        prefManager = new SliderPrefManager(this);
        viewPager = findViewById(R.id.viewPager);
        btnNext = findViewById(R.id.btn_next);
        btnSkip = findViewById(R.id.btn_skip);
        dotsLayout = findViewById(R.id.dots_layout);
        adapter = new SliderAdapterV1();
        viewPager.setAdapter(adapter);
    }

    public class SliderAdapterV1 extends PagerAdapter {

        @NonNull
        @NotNull
        @Override
        public Object instantiateItem(@NonNull @NotNull ViewGroup container, int position) {
            View view = LayoutInflater.from(SlideShowV1.this).inflate(layoutIds[position], container, false);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return layoutIds.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull @org.jetbrains.annotations.NotNull View view, @NonNull @org.jetbrains.annotations.NotNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
