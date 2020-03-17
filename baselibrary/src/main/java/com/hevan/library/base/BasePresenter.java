package com.hevan.library.base;

import android.view.View;

import com.hevan.library.data.ITasksDataSource;


/**
 * 作为View与Model交互的中间纽带，处理与用户交互的负责逻辑
 * Created by huangwx on 2016/6/15.
 */
public abstract class BasePresenter implements IBasePresenter, ILoadView {

    private ILoadView mLoadView;
    private IBaseView mBaseView;
//    private TasksNetDataSource mTasksDataSource;

    public BasePresenter(ILoadView loadView, IBaseView baseView) {
        this.mLoadView = loadView;
        this.mBaseView = baseView;
//        this.mTasksDataSource = new TasksNetDataSource();
        initTasksListener();
    }

    /**
     * 初始化请求任务处理监听事件
     */
    private void initTasksListener() {
//        mTasksDataSource.setOnConnectListener(mTasksNetworkConnectListener);
//        mTasksDataSource.setOnTimeoutListener(mTasksRequestTimeoutListener);
    }

    @Override
    public ITasksDataSource getTasksDataSource() {
        return null;
    }

    @Override
    public void showLoadView() {
        if (mLoadView != null) {
            mLoadView.showLoadView();
        }
    }

    @Override
    public void showErrorView() {
        if (mLoadView != null) {
            mLoadView.showErrorView();
        }
    }

    @Override
    public void showEmptyView() {
        if (mLoadView != null) {
            mLoadView.showEmptyView();
        }
    }

    @Override
    public void setEmptyDataRes(int imgRes, int textRes) {
        if (mLoadView != null) {
            mLoadView.setEmptyDataRes(imgRes, textRes);
        }
    }

    @Override
    public void setEmptyDataRes(int imgRes, String textRes) {
        if (mLoadView != null) {
            mLoadView.setEmptyDataRes(imgRes, textRes);
        }
    }

    @Override
    public void showConnectTimeOut() {
        if (mLoadView != null) {
            mLoadView.showConnectTimeOut();
        }
    }

    @Override
    public void hideView() {
        if (mLoadView != null) {
            mLoadView.hideView();
        }
    }

    @Override
    public void registerRequestRetry(RequestRetryListener requestRetry) {
        if (mLoadView != null) {
            if (requestRetry == null) {
                requestRetry = mRequestRetryListener;
            }
            mLoadView.registerRequestRetry(requestRetry);
        }
    }

    private RequestRetryListener mRequestRetryListener = new RequestRetryListener() {
        @Override
        public void onRequestRetry(View view) {
            onRequestRetryCallback(view);
        }
    };

    /**
     * 任务请求重试回调
     *
     * @param view
     */
    protected void onRequestRetryCallback(View view) {

    }

    @Override
    public void registerNetworkChange(NetworkChangeListener networkChange) {
        if (mLoadView != null) {
            if (networkChange == null) {
                networkChange = mNetworkChangeListener;
            }
            mLoadView.registerNetworkChange(networkChange);
        }
    }

    @Override
    public void showNetSettingView() {
        if (mLoadView != null) {
            mLoadView.showNetSettingView();
        }
    }

    @Override
    public void hideNetSettingView() {
        if (mLoadView != null) {
            mLoadView.hideNetSettingView();
        }
    }

    private NetworkChangeListener mNetworkChangeListener = new NetworkChangeListener() {
        @Override
        public void onChange(boolean isAvailable) {
            onNetworkChanged(isAvailable);
        }
    };

    protected void onNetworkChanged(boolean isAvailable) {

    }

//    private TasksNetDataSource.NetworkConnectListener mTasksNetworkConnectListener = new TasksNetDataSource.NetworkConnectListener() {
//
//        @Override
//        public void onUnNetworkConnect(BaseTask task) {
//            if (hasShowNetSetting(task.getTaskId())) {
//                showNetSettingView();
//            }
//        }
//    };

    /**
     * 请求任务时无网络连接是否显示网络提示
     *
     * @param taskId
     * @return
     */
    protected boolean hasShowNetSetting(String taskId) {
        return true;
    }

//    private TasksNetDataSource.RequestTimeoutListener mTasksRequestTimeoutListener = new TasksNetDataSource.RequestTimeoutListener() {
//
//        @Override
//        public void onRequestTimeout(BaseTask task) {
//            if (hasShowRetryView(task.getTaskId())) {
//                showConnectTimeOut();
//            }
//        }
//    };

    /**
     * 任务请求超时是否显示重试界面
     *
     * @param taskId
     * @return
     */
    protected boolean hasShowRetryView(String taskId) {
        return true;
    }
}
