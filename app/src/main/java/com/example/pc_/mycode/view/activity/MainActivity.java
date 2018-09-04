package com.example.pc_.mycode.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.pc_.mycode.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc- on 2018/5/31.
 */

public class MainActivity extends BaseActivity {


    public ViewPager viewPager;

    public String[] titles = {"首页", "订单", "我"};
    public List<Fragment> fragmentList = new ArrayList<>();

    public TabLayout tabLayout;



    @Override
    public int getLayout() {
        return R.layout.main_activity;
    }

    @Override
    public void initInstance() {

        viewPager=(ViewPager) findViewById(R.id.main_content);
        fragmentList.clear();



        tabLayout=(TabLayout)  findViewById(R.id.art_tab);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return  fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }


}
