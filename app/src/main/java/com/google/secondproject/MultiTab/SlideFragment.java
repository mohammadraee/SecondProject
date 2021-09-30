package com.google.secondproject.MultiTab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.secondproject.R;


public class SlideFragment extends Fragment {

    private int bgColor = 0;
    private int imageResId = 0;
    private String title = "";
    private String description = "";


    public static SlideFragment newSlide(int bgColor, int imageResId, String title, String description){
        SlideFragment fragment = new SlideFragment();
        Bundle args = new Bundle();
        args.putInt("bgColor", bgColor);
        args.putInt("imageResId", imageResId);
        args.putString("title", title);
        args.putString("description", description);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args == null) return;
        bgColor = args.getInt("bgColor");
        imageResId = args.getInt("imageResId");
        title = args.getString("title");
        description = args.getString("description");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.slide, container, false);
        ((ImageView) view.findViewById(R.id.image)).setImageResource(imageResId);
        ((TextView) view.findViewById(R.id.title)).setText(title);
        ((TextView) view.findViewById(R.id.description)).setText(description);
        view.findViewById(R.id.parentLayout).setBackgroundColor(bgColor);
        return view;
    }
}
