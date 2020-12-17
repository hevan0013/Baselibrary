package com.hevan.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hevan.library.R;


/**
 * 界面初次打开时的正在加载,加载异常
 * Created by huangwx on 2016/6/16.
 */
public class LoadingView extends FrameLayout implements View.OnClickListener {

    private static final int LISTENER_TYPE_NONE = 0;
    private static final int LISTENER_TYPE_RETRY = 1;
    private static final int LISTENER_TYPE_NET = 2;

    private View loading_view_rl;
    /**
     * 加载时的进度条
     */
    private ProgressBar loading_pb;
    /**
     * 网络异常与数据为空时的显示的控件
     */
    private LinearLayout request_error_ll, datas_empty_ll;
    /**
     * 没有数据时显示的图片
     */
    private ImageView datas_empty_iv;
    /**
     * 没有数据时的文本显示控件
     */
    private TextView error_des_tv, datas_empty_tv;
    /**
     * 异常时的重试
     */
    private Button error_retry_btn;
    /**
     * 加载视图监听事件
     */
    private LoadingViewListener mLoadingViewListener;
    /**
     * 响应按钮监听类型
     */
    private int mListenerType = LISTENER_TYPE_NONE;

    public LoadingView(Context context) {
        super(context);
        initViews();
        hiddenView();
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
        hiddenView();
    }

    private void initViews() {
        loading_view_rl = inflate(getContext(), R.layout.lib_view_loading, this);

        loading_pb = (ProgressBar) findViewById(R.id.loading_pb);

        request_error_ll = (LinearLayout) findViewById(R.id.request_error_ll);
        error_des_tv = (TextView) findViewById(R.id.error_des_tv);
        error_retry_btn = (Button) findViewById(R.id.error_retry_btn);
        error_retry_btn.setOnClickListener(this);

        datas_empty_ll = (LinearLayout) findViewById(R.id.datas_empty_ll);
        datas_empty_iv = (ImageView) findViewById(R.id.datas_empty_iv);
        datas_empty_tv = (TextView) findViewById(R.id.datas_empty_tv);
    }

    /**
     * 显示加载进度条的视图
     */
    public void showLoadingView() {
        setVisibility(View.VISIBLE);
        loading_pb.setVisibility(View.VISIBLE);
        request_error_ll.setVisibility(View.GONE);
        datas_empty_ll.setVisibility(View.GONE);
    }

    /**
     * 显示请求错误的视图
     */
    public void showErrorView() {
        setVisibility(View.VISIBLE);
        loading_pb.setVisibility(View.GONE);
        request_error_ll.setVisibility(View.VISIBLE);
        datas_empty_ll.setVisibility(View.GONE);
    }

    /**
     * 显示请求空白的视图
     */
    public void showEmptyView() {
        setVisibility(View.VISIBLE);
        loading_pb.setVisibility(View.GONE);
        request_error_ll.setVisibility(View.VISIBLE);
        datas_empty_ll.setVisibility(View.VISIBLE);
    }

    /**
     * 设置显示空状态时的图片资源与描述
     *
     * @param imgRes
     * @param textRes
     */
    public void setEmptyDataRes(int imgRes, int textRes) {
        datas_empty_iv.setImageResource(imgRes);
        datas_empty_tv.setText(textRes);
        showEmptyView();
    }

    /**
     * 设置显示空状态时的图片资源与描述
     *
     * @param imgRes
     * @param textRes
     */
    public void setEmptyDataRes(int imgRes, String textRes) {
        datas_empty_iv.setImageResource(imgRes);
        datas_empty_tv.setText(textRes);
        showEmptyView();
    }

    /**
     * 展示界面网络超时状态
     */
    public void showConnectTimeOut() {
        mListenerType = LISTENER_TYPE_RETRY;
        error_des_tv.setText(R.string.request_error_timeout);
        if (mLoadingViewListener != null) {
            error_retry_btn.setText(R.string.request_retry);
            error_retry_btn.setVisibility(View.VISIBLE);
        } else {
            error_retry_btn.setVisibility(View.GONE);
        }
        showErrorView();
    }

    /**
     * 设置加载视图监听事件
     *
     * @param loadingViewListener
     */
    public void setOnLoadingViewListener(LoadingViewListener loadingViewListener) {
        this.mLoadingViewListener = loadingViewListener;
    }

    /**
     * 展示界面无网络状态
     */
    public void showNoConnection() {
        mListenerType = LISTENER_TYPE_NET;
        error_des_tv.setText(R.string.error_noconnect);
        if (mLoadingViewListener != null) {
            error_retry_btn.setText(R.string.setting_network);
            error_retry_btn.setVisibility(View.VISIBLE);
        } else {
            error_retry_btn.setVisibility(View.GONE);
        }
        showErrorView();
    }

    /**
     * 影响此控件
     */
    public void hiddenView() {
        mListenerType = LISTENER_TYPE_NONE;
        this.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (mListenerType) {
            case LISTENER_TYPE_RETRY:
                if (mLoadingViewListener != null) {
                    mLoadingViewListener.onRequestRetry(v);
                }
                break;
            case LISTENER_TYPE_NET:
                if (mLoadingViewListener != null) {
                    mLoadingViewListener.onNetworkSetting(v);
                }
                break;
        }
    }

    public interface LoadingViewListener {

        void onRequestRetry(View view);

        void onNetworkSetting(View view);
    }
}
