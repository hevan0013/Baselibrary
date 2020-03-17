package com.hevan.library.base;

import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.hevan.library.R;

import butterknife.ButterKnife;

public abstract class AbsToolbarActivity extends BaseActivity {

    protected AppBarLayout mAppBarLayout;
    protected Toolbar mToolbar;

    /**
     * 获取基础的toolbar布局
     *
     * @return
     */
    @Override
    protected int getBaseLayoutResId() {
        return R.layout.base_toolbar_layout;
    }

    /**
     * ActionBar布局内容资源id
     */
    protected int getActionBarResId() {
        return R.layout.base_actionbar;
    }

    @Override
    protected void initContainerView() {
        mAppBarLayout = findViewById(R.id.base_app_bar);
        mToolbar = findViewById(R.id.toolbar);
        if (mToolbar == null) {
            throw new RuntimeException("can not find ToolBar id=toolbar");
        }
        fl_base_content = findViewById(R.id.fl_base_content);

        if (fl_base_content == null) {
            throw new RuntimeException("can not find FrameLayout id=fl_base_content");
        }

        initActionBarContentViews();
        View view = mInflater.inflate(getLayoutResource(), fl_base_content, false);
        fl_base_content.addView(view);
        unbinder = ButterKnife.bind(this);

        initViews(view);
    }

    /**
     * 初始化actionbar, 界面控件
     */
    private void initActionBarContentViews() {
        //需要使用NoActionBar主题才能设置成功
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        View actionView = mInflater.inflate(getActionBarResId(), null, false);
        if (actionView != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setCustomView(actionView, getActionBarCustomLayoutParams());
        }
    }

    /**
     * actionBar自定义标题中的参数
     */
    protected ActionBar.LayoutParams getActionBarCustomLayoutParams() {
        return new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT);
    }
}
