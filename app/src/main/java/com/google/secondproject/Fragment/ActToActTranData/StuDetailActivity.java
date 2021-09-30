package com.google.secondproject.Fragment.ActToActTranData;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.secondproject.Fragment.ActToActTranData.Student;
import com.google.secondproject.Fragment.DialogFrag.StuDetailsFrag;
import com.google.secondproject.R;

public class StuDetailActivity extends AppCompatActivity {

    StuDetailsFrag frag;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_detail);

        student = getIntent().getParcelableExtra("student");
        if (student == null) {
            finish();
            return;
        }

        frag = StuDetailsFrag.getInstance(student.getName(), student.getId(), student.getScore());
        getSupportFragmentManager().beginTransaction().replace(R.id.student_detail_container, frag, null).commit();
    }
}