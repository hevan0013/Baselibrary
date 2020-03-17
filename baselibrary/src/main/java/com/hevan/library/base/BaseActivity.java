package com.hevan.library.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hevan.library.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by huangwx on 2016/6/15.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    protected FrameLayout fl_base_content;
    /**
     * 布局加载
     */
    protected LayoutInflater mInflater;
    /**
     * 注册的广播
     */
    protected BroadcastReceiver mReceiver;

    protected Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mInflater = getLayoutInflater();

        initBroadcastReceiver();
        setContentView(getBaseLayoutResId());
        initContainerView();

        onViewCreated(savedInstanceState);
    }

    /**
     * 获取基础内容视图资源id
     *
     * @return
     */
    protected int getBaseLayoutResId() {
        return R.layout.base_layout;
    }

    /**
     * 获取内容视图资源id
     *
     * @return
     */
    protected abstract int getLayoutResource();

    /**
     * 初始化根目录控件
     */
    protected void initContainerView() {
        fl_base_content = findViewById(R.id.fl_base_content);
        if (fl_base_content == null) {
            throw new RuntimeException("can not find FrameLayout id=fl_base_content");
        }
        View view = mInflater.inflate(getLayoutResource(), fl_base_content, false);
        fl_base_content.addView(view);
        unbinder = ButterKnife.bind(this);
        initViews(view);
    }

    /**
     * 初始化控件
     *
     * @param root activity根目录
     */
    protected void initViews(View root) {

    }

    /**
     * 内容视图创建完成后回调
     *
     * @param savedInstanceState
     */
    protected void onViewCreated(Bundle savedInstanceState) {
    }

    /**
     * 初始化广播监听
     */
    private void initBroadcastReceiver() {
        String[] actions = registerActions();
        if (actions != null && actions.length > 0) {
            mReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    if (BaseActivity.this.isFinishing() || TextUtils.isEmpty(intent.getAction())) {
                        return;
                    }

                    BaseActivity.this.onReceive(intent.getAction(), intent);
                }
            };
            IntentFilter filter = new IntentFilter();
            for (String action : actions) {
                filter.addAction(action);
            }
            registerReceiver(mReceiver, filter);
        }
    }

    /**
     * 注册广播
     *
     * @return null不注册
     */
    protected String[] registerActions() {
        return null;
    }

    /**
     * registerActions不为空时,可监听此广播
     *
     * @param action 广播事件
     * @param intent
     */
    protected void onReceive(String action, Intent intent) {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReceiver != null) {
            this.unregisterReceiver(mReceiver);
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
