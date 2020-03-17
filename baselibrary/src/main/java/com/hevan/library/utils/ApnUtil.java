package com.hevan.library.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

/**
 * Created by huangwx on 2016/6/17.
 */
public class ApnUtil {

    /**
     * 判断是否有网络连接
     *
     * @return
     */
    public static boolean isNetAvailable(Context context) {
        return isConnected(context);
    }

    /**
     * 判断网络是否连接上
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {
        NetworkInfo networkInfo = getActiveNetworkInfo(context);
        if (networkInfo != null) {
            if (networkInfo.getState().compareTo(NetworkInfo.State.CONNECTED) == 0) {
                return true;
            }
        }
        return false;
    }

    private static NetworkInfo getActiveNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo;
    }

    /**
     * 到系统的设置网络界面
     *
     * @param context
     */
    public static void gotoSettingActivity(Context context) {
        try {
            String action = android.provider.Settings.ACTION_WIRELESS_SETTINGS;
            if (Build.VERSION.SDK_INT >= 14) {// 4.0的设置
                action = android.provider.Settings.ACTION_SETTINGS;
            }
            Intent intent = new Intent(action);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
        }
    }
}
