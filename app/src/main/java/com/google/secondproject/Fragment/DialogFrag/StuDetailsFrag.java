package com.google.secondproject.Fragment.DialogFrag;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.secondproject.R;

import org.jetbrains.annotations.NotNull;

public class StuDetailsFrag extends DialogFragment {

    EditText stuName, stuId, stuScore;

    public static StuDetailsFrag getInstance(String name, String id, int score) {
        StuDetailsFrag frag = new StuDetailsFrag();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("id", id);
        args.putInt("score", score);
        frag.setArguments(args);
        return frag;
    }
    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        if (window == null) return;
        WindowManager.LayoutParams params = window.getAttributes();
        Point point = getScreenSize(getActivity());
        params.width = (int) (0.9 * point.x);
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
    }


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater,
                             @Nullable @org.jetbrains.annotations.Nullable ViewGroup container,
                             @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.stu_details_frag, container, false);
        stuName = rootView.findViewById(R.id.stu_name_input);
        stuId = rootView.findViewById(R.id.stu_id_input);
        stuScore = rootView.findViewById(R.id.stu_score_input);

        stuName.setText(getArguments().getString("name"));
        stuId.setText(getArguments().getString("id"));
        stuScore.setText(String.valueOf(getArguments().getInt("score")));
        return rootView;
    }

    private Point getScreenSize(Activity activity) {
        Point point = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(point);
        return point;
    }
}
