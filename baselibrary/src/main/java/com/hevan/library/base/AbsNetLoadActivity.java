package com.hevan.library.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.hevan.library.R;
import com.hevan.library.utils.ApnUtil;
import com.hevan.library.widget.LoadingView;


/**
 * Created by huangwx on 2016/6/16.
 */
public abstract class AbsNetLoadActivity extends BaseActivity implements ILoadView, LoadingView.LoadingViewListener {

    private FrameLayout content_fl;
    private LoadingView loading_view;
    /**
     * 请求重试监听事件
     */
    private ILoadAction.RequestRetryListener mRequestRetryListener;
    /**
     * 请求网络设置监听事件
     */
    private INetLoadAction.NetworkChangeListener mNetworkChangeListener;

    @Override
    protected int getLayoutResource() {
        return R.layout.base_net_load_layout;
    }

    @Override
    protected void onViewCreated(View view, Bundle savedInstanceState) {

        loading_view = (LoadingView) view.findViewById(R.id.loading_view);

        content_fl = (FrameLayout) view.findViewById(R.id.content_fl);
        View contentView = mInflater.inflate(getContentLayoutResource(), content_fl, false);
        content_fl.addView(contentView);

        initViews(contentView, savedInstanceState);
    }

    protected abstract int getContentLayoutResource();

    protected abstract void initViews(View contentView, Bundle savedInstanceState);

    @Override
    public void showLoadView() {
        loading_view.showLoadingView();
    }

    @Override
    public void showErrorView() {
        loading_view.showErrorView();
    }

    @Override
    public void showEmptyView() {
        loading_view.showEmptyView();
    }

    @Override
    public void setEmptyDataRes(int imgRes, int textRes) {
        loading_view.setEmptyDataRes(imgRes, textRes);
    }

    @Override
    public void setEmptyDataRes(int imgRes, String textRes) {
        loading_view.setEmptyDataRes(imgRes, textRes);
    }

    @Override
    public void showConnectTimeOut() {
        loading_view.showConnectTimeOut();
    }

    @Override
    public void hideView() {
        loading_view.hiddenView();
    }

    @Override
    public void registerRequestRetry(RequestRetryListener requestRetry) {
        mRequestRetryListener = requestRetry;
    }

    public void registerNetworkChange(INetLoadAction.NetworkChangeListener networkChange) {
        mNetworkChangeListener = networkChange;
    }

    public void showNetSettingView() {
        loading_view.showNoConnection();
    }

    public void hideNetSettingView() {
        loading_view.hiddenView();
    }

    public class NetworkBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            onNetworkChanged(ApnUtil.isNetAvailable(context));
        }
    }

    protected void onNetworkChanged(final boolean netAvailable) {
        if (mNetworkChangeListener != null) {
            mNetworkChangeListener.onChange(netAvailable);
        }
    }

    @Override
    public void onRequestRetry(View view) {
        if (mRequestRetryListener != null) {
            mRequestRetryListener.onRequestRetry(view);
        }
    }

    @Override
    public void onNetworkSetting(View view) {
        ApnUtil.gotoSettingActivity(this);
    }
}
