package com.hevan.library.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;

import com.hevan.library.utils.ApnUtil;
import com.hevan.library.widget.LoadingView;


/**
 * Created by huangwx on 2016/6/16.
 */
public abstract class AbsNetLoadActivity extends AbsToolbarActivity implements ILoadView, LoadingView.LoadingViewListener {

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
    public void showLoadView() {
        if (loading_view != null) {
            loading_view.showLoadingView();
        }
    }

    @Override
    public void showErrorView() {
        if (loading_view != null) {
            loading_view.showErrorView();
        }
    }

    @Override
    public void showEmptyView() {
        if (loading_view != null) {
            loading_view.showEmptyView();
        }
    }

    @Override
    public void setEmptyDataRes(int imgRes, int textRes) {
        if (loading_view != null) {
            loading_view.setEmptyDataRes(imgRes, textRes);
        }
    }

    @Override
    public void setEmptyDataRes(int imgRes, String textRes) {
        if (loading_view != null) {
            loading_view.setEmptyDataRes(imgRes, textRes);
        }
    }

    @Override
    public void showConnectTimeOut() {
        if (loading_view != null) {
            loading_view.showConnectTimeOut();
        }
    }

    @Override
    public void hideView() {
        if (loading_view != null) {
            loading_view.hiddenView();
        }
    }

    @Override
    public void registerRequestRetry(RequestRetryListener requestRetry) {
        mRequestRetryListener = requestRetry;
    }

    @Override
    public void registerNetworkChange(INetLoadAction.NetworkChangeListener networkChange) {
        mNetworkChangeListener = networkChange;
    }

    public void showNetSettingView() {
        if (loading_view != null) {
            loading_view.showNoConnection();
        }
    }

    public void hideNetSettingView() {
        if (loading_view != null) {
            loading_view.hiddenView();
        }
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
