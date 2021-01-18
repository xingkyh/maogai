package com.example.maogai.othersActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.maogai.MainActivity;

public class LunchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        new Thread( new Runnable( ) {
            @Override
            public void run() {
                //耗时任务，比如加载网络数据
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 这里睡几秒钟
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent();
                        intent.setClass(LunchActivity.this, MainActivity.class);
                        startActivity(intent);
                        LunchActivity.this.finish();
                    }
                });
            }
        } ).start();
    }
}
