package com.google.secondproject.MultiTab;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.secondproject.R;

import java.util.ArrayList;
import java.util.List;

public class SimpleTabActivity extends AppCompatActivity {

    ViewPager viewpager;
    TabLayout tabs;
    SlidePagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_tab);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        //remove ActionBar
        getSupportActionBar().hide();
        changeStatusBarColor();

        viewpager = (ViewPager) findViewById(R.id.viewpager);
        tabs = (TabLayout) findViewById(R.id.tabLayout);
        setUpViewPager();
        tabs.setupWithViewPager(viewpager);
        String type = getIntent().getStringExtra("type");
        if ("simple".equals(type)) {
            tabs.setTabMode(TabLayout.MODE_FIXED);
        } else if ("scrollable".equals(type)) {
            tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
            prepareSlides();
        } else if ("IconAndText".equals(type)) {
            tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
            setUpTabIcons();
        } else if ("onlyIcon".equals(type)) {
            tabs.setTabGravity(TabLayout.GRAVITY_CENTER);
            tabs.setTabMode(TabLayout.MODE_FIXED);
            setUpTabIcons();
            for (int i = 0; i < tabs.getTabCount(); i++)
                tabs.getTabAt(i).setText("");
        }

    }

    public void changeStatusBarColor() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void setUpTabIcons() {
        if (tabs.getTabCount() < 4)
            return;
        tabs.getTabAt(0).setIcon(R.drawable.ic_food);
        tabs.getTabAt(1).setIcon(R.drawable.ic_movie);
        tabs.getTabAt(2).setIcon(R.drawable.ic_discount);
        tabs.getTabAt(3).setIcon(R.drawable.ic_travel);
    }

    private void prepareSlides() {
        String[] titles = getResources().getStringArray(R.array.slide_titles);
        String[] descriptions = getResources().getStringArray(R.array.slide_desc);
        int[] bgColors = new int[]{R.color.slide_1_bg_color, R.color.slide_2_bg_color,
                R.color.slide_3_bg_color, R.color.slide_4_bg_color};
        int[] imageIds = new int[]{R.drawable.ic_food, R.drawable.ic_movie,
                R.drawable.ic_discount, R.drawable.ic_travel};
        for (int i = 0; i < 4; i++) {
            pagerAdapter.addFragment(
                    SlideFragment.newSlide(ContextCompat.getColor(this, bgColors[i]),
                            imageIds[i], titles[i], descriptions[i]),
                    titles[i]);
        }
    }

    private void setUpViewPager() {
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(pagerAdapter);
        prepareSlides();
    }

    public class SlidePagerAdapter extends FragmentPagerAdapter {
        List<Fragment> fragments;
        List<String> tabTitles;

        public SlidePagerAdapter(FragmentManager fm) {
            super(fm);
            fragments = new ArrayList<>();
            tabTitles = new ArrayList<>();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        public void addFragment(Fragment fragment, String tabTitle) {
            fragments.add(fragment);
            tabTitles.add(tabTitle);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles.get(position);
        }
    }

}