package com.hevan.library.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.hevan.library.R;
import com.hevan.library.utils.ApnUtil;
import com.hevan.library.widget.LoadingView;

/**
 * Created by huangwx on 2016/6/20.
 */
public abstract class AbsNetLoadFragment extends BaseFragment implements LoadingView.LoadingViewListener {

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_fragment_net_load, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loading_view = view.findViewById(R.id.loading_view);

        content_fl = view.findViewById(R.id.content_fl);
        View contentView = getActivity().getLayoutInflater().inflate(getContentLayoutResource(), content_fl, false);
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
        this.mRequestRetryListener = requestRetry;
    }

    @Override
    public void registerNetworkChange(NetworkChangeListener networkChange) {
        this.mNetworkChangeListener = networkChange;
    }

    @Override
    public void showNetSettingView() {
        loading_view.showNoConnection();
    }

    @Override
    public void hideNetSettingView() {
        loading_view.hiddenView();
    }

    @Override
    public void onRequestRetry(View view) {
        if (mRequestRetryListener != null) {
            mRequestRetryListener.onRequestRetry(view);
        }
    }

    @Override
    public void onNetworkSetting(View view) {
        ApnUtil.gotoSettingActivity(getContext());
    }
}
