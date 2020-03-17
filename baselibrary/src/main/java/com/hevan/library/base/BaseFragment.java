package com.hevan.library.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by huangwx on 2016/6/20.
 */
public abstract class BaseFragment extends Fragment implements ILoadView {

    private ILoadView mLoadView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() instanceof ILoadView) {
            mLoadView = (ILoadView) getActivity();
        }
    }

    private ILoadView getLoadView() {
        return mLoadView;
    }

    @Override
    public void showLoadView() {
        if (getLoadView() != null) {
            getLoadView().showLoadView();
        }
    }

    @Override
    public void showErrorView() {
        if (getLoadView() != null) {
            getLoadView().showErrorView();
        }
    }

    @Override
    public void showEmptyView() {
        if (getLoadView() != null) {
            getLoadView().showEmptyView();
        }
    }

    @Override
    public void setEmptyDataRes(int imgRes, int textRes) {
        if (getLoadView() != null) {
            getLoadView().setEmptyDataRes(imgRes, textRes);
        }
    }

    @Override
    public void setEmptyDataRes(int imgRes, String textRes) {
        if (getLoadView() != null) {
            getLoadView().setEmptyDataRes(imgRes, textRes);
        }
    }

    @Override
    public void showConnectTimeOut() {
        if (getLoadView() != null) {
            getLoadView().showConnectTimeOut();
        }
    }

    @Override
    public void hideView() {
        if (getLoadView() != null) {
            getLoadView().hideView();
        }
    }

    @Override
    public void registerRequestRetry(RequestRetryListener requestRetry) {
        if (getLoadView() != null) {
            getLoadView().registerRequestRetry(requestRetry);
        }
    }

    @Override
    public void registerNetworkChange(NetworkChangeListener networkChange) {
        if (getLoadView() != null) {
            getLoadView().registerNetworkChange(networkChange);
        }
    }

    @Override
    public void showNetSettingView() {
        if (getLoadView() != null) {
            getLoadView().showNetSettingView();
        }
    }

    @Override
    public void hideNetSettingView() {
        if (getLoadView() != null) {
            getLoadView().hideNetSettingView();
        }
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
        Toast toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        toast.show();
    }
}
