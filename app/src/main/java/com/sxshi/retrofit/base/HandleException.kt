package com.sxshi.retrofit.base

import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * 统一处理异常工具类
 */
class HandleException(private val baseView: IBaseView) {
    fun handlerExcep(t: Throwable) {
        when (t) {
            is HttpException -> {
                val responseBody = t.response()!!.errorBody()
                baseView.onUnknownError(getErrorMessage(responseBody))
            }
            is SocketTimeoutException -> {
                baseView.onTimeout()
            }
            is IOException -> {
                baseView.onNetworkError()
            }
            else -> {
                baseView.onUnknownError(t.message)
            }
        }
    }

    private fun getErrorMessage(responseBody: ResponseBody?): String? {
        return if (responseBody == null) {
            "UnknownError"
        } else try {
            val jsonObject = JSONObject(responseBody.string())
            jsonObject.getString("message")
        } catch (e: Exception) {
            e.message
        }
    }

}