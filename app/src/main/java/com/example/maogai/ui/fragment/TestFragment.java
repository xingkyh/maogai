package com.example.maogai.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.maogai.R;
import com.example.maogai.othersActivity.ExamActivity;

public class TestFragment extends Fragment {
    private View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_test, container, false);
        Button button = (Button) root.findViewById(R.id.button_begin_test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ExamActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }
}
