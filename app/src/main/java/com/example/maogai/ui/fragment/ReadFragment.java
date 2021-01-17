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
import com.example.maogai.othersActivity.readActicity;

public class ReadFragment extends Fragment {
    private View root;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_read, container, false);
        Button button1 = (Button) root.findViewById(R.id.read1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), readActicity.class);
                intent.putExtra("name","1");
                startActivity(intent);
            }
        });
        Button button2 = (Button) root.findViewById(R.id.read2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), readActicity.class);
                intent.putExtra("name","2");
                startActivity(intent);
            }
        });
        Button button3 = (Button) root.findViewById(R.id.read3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), readActicity.class);
                intent.putExtra("name","3");
                startActivity(intent);
            }
        });
        Button button4 = (Button) root.findViewById(R.id.read4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), readActicity.class);
                intent.putExtra("name","4");
                startActivity(intent);
            }
        });
        return root;
    }
}
