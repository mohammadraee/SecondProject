package com.google.secondproject.Fragment.SimpleFrag;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.secondproject.R;

import org.jetbrains.annotations.NotNull;

public class MyFrag extends Fragment {
    Button fragSubmitBtn;
    EditText fragEmail;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater,
                             @Nullable @org.jetbrains.annotations.Nullable ViewGroup container,
                             @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.my_frag, container, false);
        fragEmail = rootView.findViewById(R.id.frag_input_email);
        fragSubmitBtn = rootView.findViewById(R.id.frag_btn_submit);
        fragSubmitBtn.setEnabled(false);
        fragEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String email = s.toString().trim();
                if (email.isEmpty() || !email.contains("@") ||
                        email.lastIndexOf('@') > email.lastIndexOf('.') ||
                        email.split("@").length != 2 ||
                        email.indexOf('@') == 0 || email.charAt(email.length() - 1) == '.') {
                    fragSubmitBtn.setEnabled(false);
                } else {
                    fragSubmitBtn.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        fragSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = fragEmail.getText().toString().trim();
                Toast.makeText(rootView.getContext(), email, Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}
