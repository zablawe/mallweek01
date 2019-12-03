package com.bawei.zhangao123.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView(inniLayout());
        inniView();
        inniDate();
    }

    protected abstract void inniDate();


    protected abstract void inniView();


    protected abstract int inniLayout();

}
