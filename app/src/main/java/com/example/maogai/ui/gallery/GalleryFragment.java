package com.example.maogai.ui.gallery;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.maogai.R;
import com.example.maogai.sqliteUtil.SqlPage;
import com.example.maogai.sqliteUtil.SqlUtil;
import com.example.maogai.ui.fragment.WebViewFragment;

import java.util.List;

public class GalleryFragment extends Fragment {

    View root;
    List<SqlPage> sqlPages;//查询到的历史
    int index;//点击的记录

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_gallery, container, false);
        init();
        return root;
    }

    private void init(){
        SqlUtil sqlUtil = new SqlUtil(getActivity());
        sqlPages = sqlUtil.getALLHistory();
        LinearLayout linearLayout = (LinearLayout) root.findViewById(R.id.fragment_layout_history);
        for (int i = sqlPages.size() - 1; i >= 0; i--){
            TextView textView = new TextView(getActivity());
            SqlPage sp = sqlPages.get(i);
            textView.setText(sp.getChapter() + "：" + sp.getName());
            textView.setTextSize(25);
            final int j = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    index = j;
                    showFragment();
                }
            });
            linearLayout.addView(textView);
        }
    }

    private void showFragment(){
        Fragment fragment = new WebViewFragment();
        Bundle bundle = new Bundle();
        SqlPage sqlPage = sqlPages.get(index);
        bundle.putSerializable("chapter", sqlPage.getChapter());
        bundle.putSerializable("name", sqlPage.getName());
        bundle.putSerializable("url", sqlPage.getUrl());
        bundle.putSerializable("isReturn", "true");
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }
}