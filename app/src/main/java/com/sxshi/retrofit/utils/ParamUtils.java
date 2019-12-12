package com.sxshi.retrofit.utils;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * 公共参数封装
 * 项目公用的请求参数统一在这里处理
 */
public class ParamUtils {

    public static RequestBody getRequestBody(Map<String, String> params) {
        String json = new Gson().toJson(params);

        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
    }

    public static RequestBody getRequestBody(String attributeValue) {
        HashMap<String, String> params = new HashMap<>();
        params.put("attributeName", attributeValue);//真实项目替换成真实的参数
        String json = new Gson().toJson(params);

        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
    }
}
