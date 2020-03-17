package com.hevan.libdemo;

import android.os.Bundle;

import com.hevan.libdemo.ui.main.MainFragment;
import com.hevan.library.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.main_activity;
    }
}
