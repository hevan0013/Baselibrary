package com.hevan.library.base;

import android.view.View;

/**
 * 加载数据是的进度界面
 * Created by huangwx on 2016/6/15.
 */
public interface ILoadAction {

    /**
     * 显示加载数据的视图
     */
    void showLoadView();

    /**
     * 示请求错误的视图
     */
    void showErrorView();

    /**
     * 示请求空白的视图
     */
    void showEmptyView();

    /**
     * 置显示空状态时的图片资源与描述
     *
     * @param imgRes
     * @param textRes
     */
    void setEmptyDataRes(int imgRes, int textRes);

    /**
     * 设置显示空状态时的图片资源与描述
     *
     * @param imgRes
     * @param textRes
     */
    void setEmptyDataRes(int imgRes, String textRes);

    /**
     * 展示界面网络超时状态
     */
    void showConnectTimeOut();

    /**
     * 隐藏加载数据的View
     */
    void hideView();

    /**
     * 注册请求重试监听事件
     *
     * @param requestRetry
     */
    void registerRequestRetry(RequestRetryListener requestRetry);

    interface RequestRetryListener {
        void onRequestRetry(View view);
    }
}
