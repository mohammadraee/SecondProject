package com.google.secondproject.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.secondproject.Fragment.ListFrag.FragStuList;
import com.google.secondproject.Fragment.PreferenceFrag.FragPrefActivity;
import com.google.secondproject.Fragment.ScreenSize.ScreenUtility;
import com.google.secondproject.R;
import com.google.secondproject.Fragment.DialogFrag.StuDetailsFrag;
import com.google.secondproject.Fragment.ActToActTranData.Student;

public class FragmentActivity extends AppCompatActivity implements FragStuList.callBack {

    Boolean isLargeScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

//        MyFrag frag = new MyFrag();
//        StuDetailsFrag frag = StuDetailsFrag.getInstance("mohammad", "9712762750", 18);
        FragStuList frag = new FragStuList();
        getSupportFragmentManager().beginTransaction().replace(R.id.student_name_container, frag, null).commit();
        String tag = (String) findViewById(R.id.student_name_container).getTag();

        //approach 1
//        isLargeScreen = (tag.equals("large"));

        //approach 2
        isLargeScreen = findViewById(R.id.student_detail_container) != null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("screen size").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                showScreenSizeDialog();
                return false;
            }
        });
        menu.add("frag prefs").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(getApplicationContext(), FragPrefActivity.class));
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void showScreenSizeDialog() {
        ScreenUtility screen = new ScreenUtility(this);
        String msg = "width = " + screen.getDpWidth() + ", height = " + screen.getDpHeight();
        new AlertDialog.Builder(this)
                .setTitle("screen size")
                .setMessage(msg)
                .show();
    }

    @Override
    public void onItemSelected(Student student) {
        StuDetailsFrag frag = StuDetailsFrag.getInstance(student.getName(), student.getId(), student.getScore());
        if (isLargeScreen) {
            getSupportFragmentManager().beginTransaction().replace(R.id.student_detail_container, frag, null).commit();
        } else {
//            Intent intent = new Intent(FragmentActivity.this, StuDetailActivity.class);
//            intent.putExtra("student", student);
//            startActivity(intent);
            frag.show(getSupportFragmentManager(), "student_details");
        }
    }
}