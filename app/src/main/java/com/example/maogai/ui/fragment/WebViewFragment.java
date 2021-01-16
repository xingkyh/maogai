package com.example.maogai.ui.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.maogai.R;

public class WebViewFragment extends Fragment{
    private WebView web1;
    private View root;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_webview, container, false);
        web1 = (WebView) root.findViewById(R.id.webview);;
        web1.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        //设置属性，使之能执行javascript脚本
        web1.getSettings().setJavaScriptEnabled(true);
        String url = (String) getArguments().get("url");
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
        return root;
    }


}
