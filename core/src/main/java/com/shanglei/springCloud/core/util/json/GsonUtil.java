package com.shanglei.springCloud.core.util.json;


import cn.hutool.core.util.StrUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Gson工具类
 * 注：实际转换对象中存在null属性时，转换出来的JSON字符串将不会保留该属性
 */
public class GsonUtil {


    private static final Gson gson = new Gson();


    /**
     * 缺省时间格式，开发人员可根据项目需要更改此常量
     */
    private static final String PATTERN = "yyyy-MM-dd";


    /**
     * 将一个对象转换为JSON字符串，使用缺省时间格式
     * @param object    要进行转换的对象
     * @return          JSON字符串，时间格式为java.util.Date类的toString()的结果
     */
    public static String getJson(Object object) {
        return gson.toJson(object);
    }

    /**
     * 将一个对象转换成JSON字符串，并进行时间格式化
     * @param object    要进行转换的对象
     * @param pattern   时间类型转换格式，若该参数为null或""时，使用缺省时间格式，实际调用时应当注意时间格式的有效性
     * @return
     */
    public static String getJson(Object object,String pattern) {

        if (StrUtil.isBlank(pattern)) {
            return new GsonBuilder().setDateFormat(GsonUtil.PATTERN).create().toJson(object);
        }

        return new GsonBuilder().setDateFormat(pattern).create().toJson(object);
    }


    /**
     * 将一个JSON字符串转换成一个对象,需要注意json字符串中不能有时间格式属性（Gson对时间格式缺省情况下支持的不多），若有时间类型的属性，需要使用getObject(String json, Class c, String pattern)
     * @param json  要转换的JSON字符串
     * @param c     转换类型
     * @return
     */
    public static Object getObject(String json,Class c) {
        return gson.fromJson(json, c);
    }

    /**
     * 处理范型
     * @param json
     * @param typeToken
     * @param <T>
     * @return
     */
    public static <T> T getObject(String json, TypeToken<T> typeToken) {
        return gson.fromJson(json, typeToken.getType());
    }

    /**
     * 将一个JSON字符串转换成一个对象，并按照对应的时间格式对时间类型的属性进行格式化
     * @param json      要转换的JSON字符串
     * @param c         转换类型
     * @param pattern   时间类型转换格式，若该参数为null或""时，使用缺省时间格式，实际调用时应当注意时间格式的有效性
     * @return
     */
    public static Object getObject(String json, Class c,String pattern) {
        if (StrUtil.isBlank(pattern)) {
            return new GsonBuilder().setDateFormat(GsonUtil.PATTERN).create().fromJson(json, c);
        }

        return new GsonBuilder().setDateFormat(pattern).create().fromJson(json, c);
    }



}
