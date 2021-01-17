package com.example.maogai.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.maogai.R;
import com.example.maogai.ui.fragment.CoursewareFragment;
import com.example.maogai.ui.fragment.PlanFragment;
import com.example.maogai.ui.fragment.ReadFragment;
import com.example.maogai.ui.fragment.TestFragment;
import com.example.maogai.ui.fragment.VideoFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private View root;
    private ViewPager viewPager;
    private RadioGroup radioGroup;

    public View onCreateView(@NonNull LayoutInflater inflater,
                              ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        initView();
        return root;
    }

    public void initView() {
        viewPager =(ViewPager) root.findViewById(R.id.fragment_vp);
        radioGroup =(RadioGroup) root.findViewById(R.id.tabs_rg);

        // 初始化碎片列表
        List<Fragment> myFragment=new ArrayList<>();
        myFragment.add(new PlanFragment());
        myFragment.add(new CoursewareFragment());
        myFragment.add(new VideoFragment());
        myFragment.add(new TestFragment());
        myFragment.add(new ReadFragment());

        // 管理碎片列表的Adapter
        FragmentPagerAdapter fpa=new MyFragmentPagerAdapter(getChildFragmentManager(), myFragment);
        viewPager.setAdapter(fpa);

        // 设置监听器
        radioGroup.setOnCheckedChangeListener(checkedChangeListener);
        viewPager.addOnPageChangeListener(pageChangeListener);
    }

    // 页面改变事件监听,主要是与RadioGroup保持一致
    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            // 界面的序号对应RadioGroup中RadioButton的顺序
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(position);
            radioButton.setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    // RadioGroup中RadioButton选中按钮的改变保持和ViewPager中碎片对应一致
    private RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            // 找到CheckId对应的编号
            for (int i=0;i < group.getChildCount();i++){
                if (group.getChildAt(i).getId() == checkedId){
                    // 相等则找到选中RadioButton对应的编号顺序,设置viewPager
                    viewPager.setCurrentItem(i);
                    return;
                }
            }
        }
    };

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> list;

        public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list=list;
        }

        @Override
        public Fragment getItem(int position) {
            return this.list == null ? null : this.list.get(position);
        }

        @Override
        public int getCount() {
            return this.list == null ? 0 : this.list.size();
        }

    }
}