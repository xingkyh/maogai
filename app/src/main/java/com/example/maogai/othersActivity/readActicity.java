package com.example.maogai.othersActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.maogai.R;

import org.apache.http.util.EncodingUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class readActicity extends AppCompatActivity implements View.OnClickListener {
    private int maxLine = 3;
    private SpannableString elipseString;//收起的文字
    private SpannableString notElipseString;//展开的文字
    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        tv = findViewById(R.id.text);
        String var = getIntent().getStringExtra("name");
        String content = "";
        try{
            InputStream in = null;

            if (var.equals("1"))
                in = getResources().openRawResource(R.raw.gcd);
            if (var.equals("2"))
                in = getResources().openRawResource(R.raw.rs);
            if (var.equals("3"))
                in = getResources().openRawResource(R.raw.dxp);
            if (var.equals("4"))
                in = getResources().openRawResource(R.raw.xy);
            int length = in.available();
            byte [] buffer = new byte[length];
            in.read(buffer);
            content = EncodingUtils.getString(buffer, "utf-8");
            //依bbi.txt的编码类型选择合适的编码，如果不调整会乱码
            in.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        tv.setText(content);//把得到的内容显示在TextView上


        //获取TextView的画笔对象
        TextPaint paint = tv.getPaint();
        //每行文本的布局宽度
        int width =getResources().getDisplayMetrics().widthPixels - dip2px(this,0);
        //实例化StaticLayout 传入相应参数
        StaticLayout staticLayout = new StaticLayout(content,paint,width, Layout.Alignment.ALIGN_NORMAL, 1, 0, false);
        //判断content是行数是否超过最大限制行数3行
        if (staticLayout.getLineCount()>maxLine) {
            //定义展开后的文本内容
            String string1 = content + "    收起";
            notElipseString = new SpannableString(string1);
            //给收起两个字设成蓝色
            notElipseString.setSpan(new ForegroundColorSpan(Color.parseColor("#0079e2")), string1.length() - 2, string1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            //获取到第三行最后一个文字的下标
            int index = staticLayout.getLineStart(maxLine) - 1;
            //定义收起后的文本内容
            String substring = content.substring(0, index - 2) + "..." + "更多";
            elipseString = new SpannableString(substring);
            //给查看全部设成蓝色
            elipseString.setSpan(new ForegroundColorSpan(Color.parseColor("#0079e2")), substring.length() - 5, substring.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            //设置收起后的文本内容
            tv.setText(elipseString);
            tv.setOnClickListener(this);
            //将textview设成选中状态 true用来表示文本未展示完全的状态,false表示完全展示状态，用于点击时的判断
            tv.setSelected(true);
        } else {
            //没有超过 直接设置文本
            tv.setText(content);
            tv.setOnClickListener(null);
        }

    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context mContext, float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() ==R.id.text) {
            if (v.isSelected()) {
                //如果是收起的状态
                tv.setText(notElipseString);
                tv.setSelected(false);
            } else {
                //如果是展开的状态
                tv.setText(elipseString);
                tv.setSelected(true);
            }
        }
    }
}