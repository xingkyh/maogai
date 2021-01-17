package com.example.maogai.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.maogai.R;

public class CoursewareFragment extends Fragment implements View.OnClickListener{
    private FragmentManager fm;
    private FragmentTransaction ft;
    private WebView web1;
    private View root;
    private String url;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button10;
    private Button button11;
    private Button button12;
    private Button button13;
    private Button button14;
    private Button button15;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fm=getFragmentManager();
        ft=fm.beginTransaction();
        root = inflater.inflate(R.layout.fragment_courseware, container, false);

        button1 = (Button)root.findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = (Button)root.findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = (Button)root.findViewById(R.id.button3);
        button3.setOnClickListener(this);
        button4 = (Button)root.findViewById(R.id.button4);
        button4.setOnClickListener(this);
        button5 = (Button)root.findViewById(R.id.button5);
        button5.setOnClickListener(this);
        button6 = (Button)root.findViewById(R.id.button6);
        button6.setOnClickListener(this);
        button7 = (Button)root.findViewById(R.id.button7);
        button7.setOnClickListener(this);
        button8 = (Button)root.findViewById(R.id.button8);
        button8.setOnClickListener(this);
        button9 = (Button)root.findViewById(R.id.button9);
        button9.setOnClickListener(this);
        button10 = (Button)root.findViewById(R.id.button10);
        button10.setOnClickListener(this);
        button11 = (Button)root.findViewById(R.id.button11);
        button11.setOnClickListener(this);
        button12 = (Button)root.findViewById(R.id.button12);
        button12.setOnClickListener(this);
        button13 = (Button)root.findViewById(R.id.button13);
        button13.setOnClickListener(this);
        button14 = (Button)root.findViewById(R.id.button14);
        button14.setOnClickListener(this);
        button15 = (Button)root.findViewById(R.id.button15);
        button15.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        url = "";
        switch (v.getId()){
            case R.id.button1:
                url = "file:///android_asset/one.html";
                break;
            case R.id.button2:
                url = "file:///android_asset/two.html";
                break;
            case R.id.button3:
                url = "file:///android_asset/three.html";
                break;
            case R.id.button4:
                url = "file:///android_asset/four.html";
                break;
            case R.id.button5:
                url = "file:///android_asset/five.html";
                break;
            case R.id.button6:
                url = "file:///android_asset/six.html";
                break;
            case R.id.button7:
                url = "file:///android_asset/seven.html";
                break;
            case R.id.button8:
                url = "file:///android_asset/eight.html";
                break;
            case R.id.button9:
                url = "file:///android_asset/nine.html";
                break;
            case R.id.button10:
                url = "file:///android_asset/ten.html";
                break;
            case R.id.button11:
                url = "file:///android_asset/eleven.html";
                break;
            case R.id.button12:
                url = "file:///android_asset/twelve.html";
                break;
            case R.id.button13:
                url = "file:///android_asset/thirteen.html";
                break;
            case R.id.button14:
                url = "file:///android_asset/fourteen.html";
                break;
            case R.id.button15:
                url = "file:///android_asset/fifteen.html";
                break;
            default:
                url = "null";

        }
        //Log.i("url",url);
        fm=getFragmentManager();
        ft=fm.beginTransaction();
        Fragment fragment= new WebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("url",url);
        fragment.setArguments(bundle);
        ft.replace(R.id.fragment_courseware, fragment);
        ft.commit();
    }
}


