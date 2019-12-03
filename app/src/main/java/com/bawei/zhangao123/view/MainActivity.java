package com.bawei.zhangao123.view;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.bawei.zhangao123.R;
import com.bawei.zhangao123.base.BaseActivity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {


    private ViewPager pager;
    private RadioGroup radioGroup;
    ArrayList <Fragment> fragmentArrayList = new ArrayList <>();
    ArrayList <String> stringArrayList = new ArrayList <>();
    @Override
    protected void inniDate() {

    }

    @Override
    protected void inniView() {
        pager = findViewById( R.id.vp );
        radioGroup = findViewById( R.id.rg );
        fragmentArrayList.add( new ShouFragment() );
        fragmentArrayList.add( new FenFragment() );
        fragmentArrayList.add( new FaFragment() );
        fragmentArrayList.add( new ShowFragment() );
        fragmentArrayList.add( new MyFragment() );

        stringArrayList.add( "首页" );
        stringArrayList.add( "分类" );
        stringArrayList.add( "发现" );
        stringArrayList.add( "购物车" );
        stringArrayList.add( "我的" );
        pager.setAdapter( new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragmentArrayList.get( i );
            }

            @Override
            public int getCount() {
                return fragmentArrayList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return stringArrayList.get( position );
            }
        } );
        radioGroup.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case  R.id.btn1:
                        pager.setCurrentItem( 0 );
                        break;
                    case  R.id.btn2:
                        pager.setCurrentItem( 1 );
                        break;
                    case  R.id.btn3:
                        pager.setCurrentItem( 2 );
                        break;
                    case  R.id.btn4:
                        pager.setCurrentItem( 3);
                        break;
                    case  R.id.btn5:
                        pager.setCurrentItem( 4 );
                        break;

                }
            }
        } );
           pager.addOnPageChangeListener( new ViewPager.OnPageChangeListener() {
               @Override
               public void onPageScrolled(int i, float v, int i1) {

               }

               @Override
               public void onPageSelected(int i) {
              radioGroup.check( radioGroup.getChildAt( i ).getId() );
               }

               @Override
               public void onPageScrollStateChanged(int i) {

               }
           } );


    }

    @Override
    protected int inniLayout() {
        return R.layout.activity_main;
    }


}
