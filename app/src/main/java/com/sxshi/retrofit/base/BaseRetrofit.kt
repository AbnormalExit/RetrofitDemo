package com.sxshi.retrofit.base

import com.sxshi.retrofit.BuildConfig
import com.sxshi.retrofit.http.URLContainer
import com.sxshi.retrofit.http.Webservice
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object BaseRetrofit {
    val services by lazy {
        if (BuildConfig.DEBUG) {
            getService(Webservice::class.java, URLContainer.BASE_URL_DEBUG)
        } else {
            getService(Webservice::class.java, URLContainer.BASE_URL_RELEASE)
        }
    }
    private const val TIME_OUT = 20
    private val client: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
            builder.addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            }).connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)

            handleBuilder(builder)
            return builder.build()
        }

    fun handleBuilder(builder: OkHttpClient.Builder) {

    }

    fun <S> getService(serviceClass: Class<S>, baseUrl: String): S {
        return Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
                .baseUrl(baseUrl)
                .build().create(serviceClass)
    }
}