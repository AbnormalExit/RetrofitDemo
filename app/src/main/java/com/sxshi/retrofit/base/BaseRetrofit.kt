package com.sxshi.retrofit.base

import com.sxshi.retrofit.http.URLContainer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class BaseRetrofit {
    private val builder: OkHttpClient.Builder = OkHttpClient.Builder()
    /**
     * okhttp 相关配置
     *
     * @param builder OkHttpClient.Builder
     */
    private fun handleBuilder(builder: OkHttpClient.Builder) {
        builder.addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }).connectTimeout(TIME_OUT, TimeUnit.SECONDS)
    }

    fun <T> getService(clzz: Class<T>): T {
        return Retrofit.Builder()
                .baseUrl(URLContainer.BASE_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(clzz)
    }

    companion object {
        private const val TIME_OUT: Long = 20
    }

    init {
        handleBuilder(builder)
    }
}