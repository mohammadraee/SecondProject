package com.google.secondproject.RecyclerView;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.secondproject.R;
import com.google.secondproject.RecyclerView.Divider.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<MyFriend> myFriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView = findViewById(R.id.recycler_view);
        prepareData();
        showDisplay();
    }

    private void showDisplay() {
        MyAdapter adapter = new MyAdapter(myFriends);
        // show in approach 1
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this,RecyclerView.VERTICAL));

        //show as approach 2
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        recyclerView.setAdapter(adapter);
    }

    private void prepareData() {
        if (myFriends == null) {
            myFriends = new ArrayList<>();
        } else {
            myFriends.clear();
        }
        myFriends.add(new MyFriend("Mohammad", "Amol"));
        myFriends.add(new MyFriend("Ali", "Ghaemshahr"));
        myFriends.add(new MyFriend("Ahmad", "Babol"));
        myFriends.add(new MyFriend("Arash", "Tonekabon"));
        myFriends.add(new MyFriend("Abbas", "Sari"));
        myFriends.add(new MyFriend("Alireza", "Ramsar"));
        myFriends.add(new MyFriend("Hasan", "Noshahr"));
        myFriends.add(new MyFriend("Mahdi", "Chalos"));
        myFriends.add(new MyFriend("Reza", "Neka"));
        myFriends.add(new MyFriend("Hosein", "Behshahr"));
        myFriends.add(new MyFriend("Mohammad", "Amol"));
        myFriends.add(new MyFriend("Ali", "Ghaemshahr"));
        myFriends.add(new MyFriend("Ahmad", "Babol"));
        myFriends.add(new MyFriend("Arash", "Tonekabon"));
        myFriends.add(new MyFriend("Abbas", "Sari"));
        myFriends.add(new MyFriend("Alireza", "Ramsar"));
        myFriends.add(new MyFriend("Hasan", "Noshahr"));
        myFriends.add(new MyFriend("Mahdi", "Chalos"));
        myFriends.add(new MyFriend("Reza", "Neka"));
        myFriends.add(new MyFriend("Hosein", "Behshahr"));
    }
}