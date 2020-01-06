package com.sxshi.retrofit.base;

import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * 统一处理异常工具类
 */
public class HandleException {

    private IBaseView baseView;

    public HandleException(IBaseView baseView) {
        this.baseView = baseView;
    }

    public void handlerExcep(Throwable t) {
        if (t instanceof HttpException) {
            ResponseBody responseBody = ((HttpException) t).response().errorBody();
            baseView.onUnknownError(getErrorMessage(responseBody));
        } else if (t instanceof SocketTimeoutException) {
            baseView.onTimeout();
        } else if (t instanceof IOException) {
            baseView.onNetworkError();
        } else {
            baseView.onUnknownError(t.getMessage());
        }
    }

    private String getErrorMessage(ResponseBody responseBody) {
        if (responseBody == null) {
            return "UnknownError";
        }
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.getString("message");
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
