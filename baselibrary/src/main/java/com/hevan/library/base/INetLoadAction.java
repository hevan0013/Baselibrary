package com.hevan.library.base;

/**
 * 网络请求的相关操作
 * Created by huangwx on 2016/6/15.
 */
public interface INetLoadAction {

    /**
     * 注册网络变化监听,只注册不注销实现者必须处理释放问题，建议使用弱引用
     */
    public void registerNetworkChange(NetworkChangeListener networkChange);

    /**
     * 显示这种网络的界面
     */
    public void showNetSettingView();

    /**
     * 隐藏这种网络的界面
     */
    public void hideNetSettingView();

    /**
     * 网络变化监听者
     * Created by huangwx on 2016/6/15.
     */
    public interface NetworkChangeListener {
        /**
         * 网络变化回调
         *
         * @param isAvailable
         */
        public void onChange(boolean isAvailable);
    }
}