package com.example.maogai.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.maogai.R;
import com.example.maogai.othersActivity.videoActivity;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.StyledPlayerView;


public class VideoFragment extends Fragment {
    private View root;
    private StyledPlayerView s1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =   inflater.inflate(R.layout.fragment_video, container, false);
        Button button1 = (Button) root.findViewById(R.id.video1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), videoActivity.class);
                intent.putExtra("name","1");
                startActivity(intent);
            }
        });
        Button button2 = (Button) root.findViewById(R.id.video2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), videoActivity.class);
                intent.putExtra("name","2");
                startActivity(intent);
            }
        });
        Button button3 = (Button) root.findViewById(R.id.video3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), videoActivity.class);
                intent.putExtra("name","3");
                startActivity(intent);
            }
        });
        Button button4 = (Button) root.findViewById(R.id.video4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), videoActivity.class);
                intent.putExtra("name","4");
                startActivity(intent);
            }
        });

        return root;
    }


}