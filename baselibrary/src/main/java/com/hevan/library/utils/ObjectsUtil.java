package com.hevan.library.utils;

import androidx.annotation.Nullable;

import java.util.Arrays;

/**
 * Created by huangwx on 2016/6/20.
 */
public class ObjectsUtil {

    public static boolean equal(@Nullable Object a, @Nullable Object b) {
        return a == b || (a != null && a.equals(b));
    }

    public static int hashCode(@Nullable Object... objects) {
        return Arrays.hashCode(objects);
    }
}
