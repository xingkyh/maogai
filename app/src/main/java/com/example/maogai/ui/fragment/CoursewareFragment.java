package com.example.maogai.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.maogai.R;

public class CoursewareFragment extends Fragment implements View.OnClickListener{

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_courseware, container, false);

        Button button1 = (Button) root.findViewById(R.id.button1);
        button1.setOnClickListener(this);
        Button button2 = (Button) root.findViewById(R.id.button2);
        button2.setOnClickListener(this);
        Button button3 = (Button) root.findViewById(R.id.button3);
        button3.setOnClickListener(this);
        Button button4 = (Button) root.findViewById(R.id.button4);
        button4.setOnClickListener(this);
        Button button5 = (Button) root.findViewById(R.id.button5);
        button5.setOnClickListener(this);
        Button button6 = (Button) root.findViewById(R.id.button6);
        button6.setOnClickListener(this);
        Button button7 = (Button) root.findViewById(R.id.button7);
        button7.setOnClickListener(this);
        Button button8 = (Button) root.findViewById(R.id.button8);
        button8.setOnClickListener(this);
        Button button9 = (Button) root.findViewById(R.id.button9);
        button9.setOnClickListener(this);
        Button button10 = (Button) root.findViewById(R.id.button10);
        button10.setOnClickListener(this);
        Button button11 = (Button) root.findViewById(R.id.button11);
        button11.setOnClickListener(this);
        Button button12 = (Button) root.findViewById(R.id.button12);
        button12.setOnClickListener(this);
        Button button13 = (Button) root.findViewById(R.id.button13);
        button13.setOnClickListener(this);
        Button button14 = (Button) root.findViewById(R.id.button14);
        button14.setOnClickListener(this);
        Button button15 = (Button) root.findViewById(R.id.button15);
        button15.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        String chapter = null;
        String name = null;
        String url = null;
        switch (v.getId()){
            case R.id.button1:
                chapter = "第一章";
                name = "毛泽东思想及其历史地位";
                url = "file:///android_asset/one.html";
                break;
            case R.id.button2:
                chapter = "第二章";
                name = "新民主主义革命理论";
                url = "file:///android_asset/two.html";
                break;
            case R.id.button3:
                chapter = "第三章";
                name = "社会主义改造理论";
                url = "file:///android_asset/three.html";
                break;
            case R.id.button4:
                chapter = "第四章";
                name = "社会主义建设道路初步探索的理论成果";
                url = "file:///android_asset/four.html";
                break;
            case R.id.button5:
                chapter = "第五章";
                name = "邓小平理论";
                url = "file:///android_asset/five.html";
                break;
            case R.id.button6:
                chapter = "第六章";
                name = "三个代表”重要思想";
                url = "file:///android_asset/six.html";
                break;
            case R.id.button7:
                chapter = "第七章";
                name = "科学发展观";
                url = "file:///android_asset/seven.html";
                break;
            case R.id.button8:
                chapter = "第八章";
                name = "习近平新时代中国特色社会主义思想及其历史地位";
                url = "file:///android_asset/eight.html";
                break;
            case R.id.button9:
                chapter = "第九章";
                name = "中国特色社会主义总任务";
                url = "file:///android_asset/nine.html";
                break;
            case R.id.button10:
                chapter = "第十章";
                name = "全面深化改革";
                url = "file:///android_asset/ten.html";
                break;
            case R.id.button11:
                chapter = "第十一章";
                name = "“五位一体”总体布局";
                url = "file:///android_asset/eleven.html";
                break;
            case R.id.button12:
                chapter = "第十二章";
                name = "全面推进军事现代化";
                url = "file:///android_asset/twelve.html";
                break;
            case R.id.button13:
                chapter = "第十三章";
                name = "“一国两制”与祖国统一";
                url = "file:///android_asset/thirteen.html";
                break;
            case R.id.button14:
                chapter = "第十四章";
                name = "中国特色大国外交";
                url = "file:///android_asset/fourteen.html";
                break;
            case R.id.button15:
                chapter = "第十五章";
                name = "全面从严治党";
                url = "file:///android_asset/fifteen.html";
                break;
            default:
                url = "null";

        }
        //Log.i("url",url);
        Fragment fragment= new WebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("chapter", chapter);
        bundle.putSerializable("name", name);
        bundle.putSerializable("url", url);
        fragment.setArguments(bundle);
        show(fragment);
    }

    //用于页面跳转
    private void show(Fragment fragment){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }
}


