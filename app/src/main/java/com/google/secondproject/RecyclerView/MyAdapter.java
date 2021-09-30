package com.google.secondproject.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.secondproject.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<MyFriend> myFriends;

    public MyAdapter(List<MyFriend> myFriends) {
        if (myFriends == null) this.myFriends = new ArrayList<>();
        else this.myFriends = myFriends;
    }


    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.myfriend_list_item, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyAdapter.MyViewHolder holder, int position) {
        holder.bind(myFriends.get(position));
    }

    @Override
    public int getItemCount() {
        return myFriends.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, city;

        MyViewHolder(View viewItem) {
            super(viewItem);
            name = viewItem.findViewById(R.id.recycler_view_name);
            city = viewItem.findViewById(R.id.recycler_view_city);
        }

        public void bind(MyFriend friend) {
            name.setText(friend.getName());
            city.setText(friend.getCity());
        }
    }
}
