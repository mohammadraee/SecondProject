package com.google.secondproject.Fragment.ListFrag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import com.google.secondproject.R;
import com.google.secondproject.Fragment.ActToActTranData.Student;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FragStuList extends ListFragment{
    List<Student> studentList;

    private callBack activity;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        studentList = Student.samples();
        ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(getContext(), android.R.layout.simple_list_item_1,studentList);
        setListAdapter(adapter);
        activity = (callBack)getActivity();
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater,
                             @Nullable @org.jetbrains.annotations.Nullable ViewGroup container,
                             @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_stu_list,container,false);
        return rootView;
    }

    @Override
    public void onListItemClick(@NonNull @NotNull ListView l, @NonNull @NotNull View v, int position, long id) {
        activity.onItemSelected(studentList.get(position));
        super.onListItemClick(l, v, position, id);
    }

    public interface callBack {
        public void onItemSelected(Student student);
    }
}
