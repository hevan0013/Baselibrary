package com.hevan.library.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Gson解析数据格式
 * Created by huangwx on 2016/6/20.
 */
public class GsonUtil implements JsonSerializer<Object>, JsonDeserializer<Object> {

    /**
     * 从json字符串获取List<T>
     *
     * @param src
     * @param cls
     * @return
     */
    public static <T> List<T> fromJsonArrayStr(String src, Class<T> cls) {

        ArrayList<T> result = null;

        if (!TextUtils.isEmpty(src)) {
            try {

                JSONArray jsonArray = new JSONArray(src);
                result = new ArrayList<T>();

                for (int i = 0; i < jsonArray.length(); i++) {
                    String item = jsonArray.getString(i);
                    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
                    result.add(gson.fromJson(item, cls));
                }

            } catch (JSONException e) {
            } catch (JsonSyntaxException e) {
            }
        }

        return result;
    }

    /**
     * 从json字符串获取
     *
     * @param src
     * @param cls
     * @return
     */
    public static <T> T fromJsonStr(String src, Class<T> cls) {
        T result = null;

        if (!TextUtils.isEmpty(src)) {
            try {

                Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
                result = gson.fromJson(src, cls);

            } catch (JsonSyntaxException e) {
            }
        }

        return result;
    }

    /**
     * 将数据转为json字符串
     *
     * @param value
     * @return
     */
    public static String toJsonStr(Object value) {

        Gson gson = new Gson();
        String str = gson.toJson(value);

        return str;
    }

    /**
     * 转换成json string
     *
     * @param from
     * @return
     */
    public static String toJsonArrayStr(List<?> from) {

        JSONArray result = null;
        if (from != null && from.size() > 0) {

            result = new JSONArray();

            for (Object object : from) {

                if (GsonUtil.class.isAssignableFrom(object.getClass())) {
                    String jsonObj = ((GsonUtil) object).toJsonStr();
                    if (jsonObj != null) {
                        result.put(jsonObj);
                    }
                }
            }
            return result.toString();
        }

        return null;
    }

    public String toJsonStr() {

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        return gson.toJson(this, this.getClass());
    }

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }

    @Override
    public JsonElement serialize(Object src, Type typeOfSrc, JsonSerializationContext context) {
        return null;
    }
}
