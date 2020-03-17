package com.hevan.library.base;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.WindowCompat;

import com.hevan.library.R;
import com.google.android.material.appbar.AppBarLayout;


/**
 * Created by huangwx on 2016/6/15.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Context mContext;
    /**
     * 布局加载
     */
    protected LayoutInflater mInflater;
    private FrameLayout mBaseContentFL;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mInflater = getLayoutInflater();
        supportRequestWindowFeature(WindowCompat.FEATURE_ACTION_MODE_OVERLAY);
        setContentView(R.layout.base_layout);

        mAppBarLayout = findViewById(R.id.base_app_bar);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        View actionView = onCreateActionBar(savedInstanceState);
        if (actionView != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            onCustomActionBar(actionView);
            actionBar.setCustomView(actionView, getActionBarCustomLayoutParams());
        }

        mBaseContentFL = (FrameLayout) findViewById(R.id.base_content_fl);
        View view = mInflater.inflate(getLayoutResource(), mBaseContentFL, false);
        mBaseContentFL.addView(view);
        onViewCreated(view, savedInstanceState);
    }

    /**
     * 获取内容视图资源id
     *
     * @return
     */
    protected abstract int getLayoutResource();

    /**
     * 内容视图创建完成后回调
     *
     * @param view               内容视图
     * @param savedInstanceState
     */
    protected abstract void onViewCreated(View view, Bundle savedInstanceState);

    /**
     * 初始化ActionBar,此方法是在oncreate方法中执行 ,在onCreateView前调用
     */
    protected View onCreateActionBar(Bundle savedInstanceState) {
        return null;
    }

    /**
     * 自定义ActionBar View后对ActionView Bar内的元素进行操作
     *
     * @param actionView
     */
    protected abstract void onCustomActionBar(View actionView);

    /**
     * actionBar自定义标题中的参数
     */
    protected ActionBar.LayoutParams getActionBarCustomLayoutParams() {
        return new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT);
    }

    /**
     * 显示Toolbar长按时的提示
     *
     * @param v    长按的控件
     * @param text 　显示的内容
     */
    protected final void showLongClickToast(View v, String text) {
        if (v == null || TextUtils.isEmpty(text))
            return;
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.RIGHT,
                v.getLeft() - v.getWidth(), v.getHeight());
        toast.show();
    }

    /**
     * 显示Toast 文本资源
     */
    protected final void showToast(int resId) {
        showToast(getString(resId));
    }

    /**
     * 显示Toast 文本资源
     */
    protected final void showToast(String text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.show();
    }
}
