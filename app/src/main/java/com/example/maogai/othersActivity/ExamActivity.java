package com.example.maogai.othersActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.maogai.R;
import com.example.maogai.examination.ExamDao;
import com.example.maogai.examination.Question;

import java.util.ArrayList;
import java.util.List;

public class ExamActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,
        RadioGroup.OnCheckedChangeListener{

    //  同样随机练习viewpager的四个选择按钮
    private RadioButton radom_practice_radioA;
    private RadioButton radom_practice_radioB;
    private RadioButton radom_practice_radioC;
    private RadioButton radom_practice_radioD;
    //    viewPager 做题目的
    private ViewPager viewpager;
    private static ArrayList<View> viewpagelist;
    //    实例化获得题目的类
    private ExamDao examDao;
    //   所有考试题目
    public static List<Question> arrayList;
    //  答题选项
    public int answer;
    //用于更新视图
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        examDao = new ExamDao(this);
        initData();
        init();
    }

    private void init() {
        viewpager = (ViewPager) findViewById(R.id.activity_random_practice_viewPager);
        viewpager.setOnPageChangeListener(this);
        //实例化自己定义的myadapter
        //自己定义的view的myadapter
        myadapter adapter = new myadapter();
        viewpager.setAdapter(adapter);
        //    做题的页数，做完滑动的
        int viewpagerIndex = 0;
        viewpager.setCurrentItem(viewpagerIndex);
        //用于在子线程中更新视图
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                lowerPage();
            }
        };
    }

    private void initData() {
        //        ExamDao的获取随机题目方法返回的题目赋值给arrayList
        arrayList = examDao.RandomGetTopic();
        LayoutInflater inflter = LayoutInflater.from(this);
        viewpagelist = new ArrayList<View>();
        for (int i = 0; i < arrayList.size(); i++) {
            Question q = arrayList.get(i);
            View view = inflter.inflate(R.layout.activity_exam_subject, null);
            //找viewpager题目
            //   题目显示
            TextView radom_practice_show_question = view.findViewById(R.id.radom_practice_show_question);
            //设置题目
            radom_practice_show_question.setText(String.format("%d. %s", i + 1, q.getQuestion()));

            //获得a选项内容
            radom_practice_radioA = view.findViewById(R.id.radom_practice_radioA);
            radom_practice_radioA.setText(q.getOptionA());

            //获得b选项内容
            radom_practice_radioB = view.findViewById(R.id.radom_practice_radioB);
            radom_practice_radioB.setText(q.getOptionB());

            //获取c选项内容
            radom_practice_radioC = view.findViewById(R.id.radom_practice_radioC);
            radom_practice_radioC.setText(q.getOptionC());

            //获取d选项内容
            radom_practice_radioD = view.findViewById(R.id.radom_practice_radioD);
            radom_practice_radioD.setText(q.getOptionD());

            // 做题选择
            RadioGroup radom_practice_radioGroup = view.findViewById(R.id.radom_practice_radioGroup);
            radom_practice_radioGroup.setOnCheckedChangeListener(this);

            //添加view
            viewpagelist.add(view);
        }
    }


    public void onClick(){
        initRadioButton();
        radom_practice_radioA.setEnabled(false);
        radom_practice_radioB.setEnabled(false);
        radom_practice_radioC.setEnabled(false);
        radom_practice_radioD.setEnabled(false);
    }

    public void initRadioButton() {
        radom_practice_radioA = viewpagelist.get(viewpager.getCurrentItem()).findViewById(R.id.radom_practice_radioA);
        radom_practice_radioB = viewpagelist.get(viewpager.getCurrentItem()).findViewById(R.id.radom_practice_radioB);
        radom_practice_radioC = viewpagelist.get(viewpager.getCurrentItem()).findViewById(R.id.radom_practice_radioC);
        radom_practice_radioD = viewpagelist.get(viewpager.getCurrentItem()).findViewById(R.id.radom_practice_radioD);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
//            ABCD四个选项，判断你选择的答案对不对
            case R.id.radom_practice_radioA:
//                选择之后禁止选择选项的点击
                onClick();
                //  判断答案是否正确
                answer = 1;
                if (arrayList.get(viewpager.getCurrentItem()).getAnswer() == answer) {
//                    如果对了换图片
//                    图片本来放在drawable文件夹下的 但是图片过大 就切换去Projiect模式的bitmip  xhdpi的文件夹下面，
//                    解决图片过大的问题
                    radom_practice_radioA.setButtonDrawable(R.mipmap.exercise_option_t);
//                    对了换文字颜色 （系统自带的颜色）
                    radom_practice_radioA.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                }
                else {
                    radom_practice_radioA.setButtonDrawable(R.mipmap.exercise_option_f);
                    radom_practice_radioA.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                }
                break;

            case R.id.radom_practice_radioB:
                onClick();
                answer = 2;
                if (arrayList.get(viewpager.getCurrentItem()).getAnswer() == answer) {
                    radom_practice_radioB.setButtonDrawable(R.mipmap.exercise_option_t);
                    radom_practice_radioB.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                }
                else {
                    radom_practice_radioB.setButtonDrawable(R.mipmap.exercise_option_f);
                    radom_practice_radioB.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                }
                break;

            case R.id.radom_practice_radioC:
                onClick();
                answer = 3;
                if (arrayList.get(viewpager.getCurrentItem()).getAnswer() == answer) {
                    radom_practice_radioC.setButtonDrawable(R.mipmap.exercise_option_t);
                    radom_practice_radioC.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                }
                else {
                    radom_practice_radioC.setButtonDrawable(R.mipmap.exercise_option_f);
                    radom_practice_radioC.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                }
                break;

            case R.id.radom_practice_radioD:
                onClick();
                answer = 4;
                if (arrayList.get(viewpager.getCurrentItem()).getAnswer() == answer) {
                    radom_practice_radioD.setButtonDrawable(R.mipmap.exercise_option_t);
                    radom_practice_radioD.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                }
                else {
                    radom_practice_radioD.setButtonDrawable(R.mipmap.exercise_option_f);
                    radom_practice_radioD.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                }
                break;
        }
        suspend();
        //lowerPage();
        //跳转到下一页
        //viewpager.setCurrentItem(viewpager.getCurrentItem() + 1);
    }

    public void lowerPage(){
        viewpager.setCurrentItem(viewpager.getCurrentItem() + 1);
    }

    private void suspend(){
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    Message msg = new Message();
                    handler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //    内部类  viewpager适配器   即做完题左右滑动切换下一道上一道
    class myadapter extends PagerAdapter {
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return viewpagelist.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(View container, int position) {
            ((ViewPager) container).addView(viewpagelist.get(position));
            return viewpagelist.get(position);
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView(viewpagelist.get(position));
        }

    }
    //    监听手机自带的按键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        如果点击的返回键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;
    }
}
