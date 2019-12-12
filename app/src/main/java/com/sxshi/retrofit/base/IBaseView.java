package com.sxshi.retrofit.base;

/**
 * 网络请求异常  统一处理相关
 */
public interface IBaseView {
    void showLoading(String message);

    void showLoading();

    void hideLoading();

    void onUnknownError(String error);

    void onTimeout();

    void onNetworkError();

    boolean isNetworkConnected();

    void onConnectionError();
}