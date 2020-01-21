package com.sxshi.retrofit.base

/**
 * 网络请求异常  统一处理相关
 */
interface IBaseView {
    fun showLoading(message: String?)
    fun hideLoading()
    fun onUnknownError(message: String?)
    fun onTimeout()
    fun onNetworkError()
    fun onConnectionError()
}