package com.example.maogai.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.maogai.R;
import com.example.maogai.sqliteUtil.SqlPage;
import com.example.maogai.sqliteUtil.SqlUtil;

public class WebViewFragment extends Fragment{
    private WebView web1;
    private View root;
    private String chapter;
    private String name;
    private String url;
    private ImageView imageView;
    private boolean isCollection, isReturn;//是否收藏，是否执行返回事件
    private SqlUtil sqlUtil;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_webview, container, false);
        sqlUtil = new SqlUtil(getActivity());
        web1 = (WebView) root.findViewById(R.id.webview);;
        web1.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        //设置属性，使之能执行javascript脚本
        web1.getSettings().setJavaScriptEnabled(true);
        Bundle bundle = getArguments();
        chapter = (String) bundle.get("chapter");
        name = (String) bundle.get("name");
        url = (String) bundle.get("url");
        String str = (String) bundle.get("isReturn");
        isReturn = false;
        if (str != null){
            isReturn = Boolean.parseBoolean(str);
        }
        init();
        return root;
    }

    private void init(){
        WebSettings settings = web1.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        web1.loadUrl(url);
        //以网页方式打开
        web1.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }
        });
        TextView chapterTextView = (TextView) root.findViewById(R.id.fragment_chapter);
        chapterTextView.setText(chapter);
        isCollection = sqlUtil.isCollection(chapter);
        imageView = (ImageView) root.findViewById(R.id.fragment_collection);
        if (isCollection){
            imageView.setBackgroundResource(R.drawable.ic_already_collection);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeCollection();
            }
        });
        //添加到浏览历史
        sqlUtil.addHistory(new SqlPage(chapter, name, url));
    }

    private void changeCollection(){
        if (isCollection){
            isCollection = false;
            imageView.setBackgroundResource(R.drawable.ic_no_collection);
            sqlUtil.deleteCollection(chapter);
            Toast.makeText(getActivity(), "取消收藏成功", Toast.LENGTH_LONG).show();
        }else {
            isCollection = true;
            imageView.setBackgroundResource(R.drawable.ic_already_collection);
            sqlUtil.addCollection(new SqlPage(chapter, name, url));
            Toast.makeText(getActivity(), "收藏成功", Toast.LENGTH_LONG).show();
        }
    }

    public boolean onBackPressed() {
        return isReturn;
    }
}
