package com.sxshi.retrofit.base;

import com.sxshi.retrofit.http.URLContainer;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseRetrofit {

    private static final long TIME_OUT = 20;

    private OkHttpClient.Builder builder;

    public BaseRetrofit() {
        this.builder = new OkHttpClient.Builder();
        handleBuilder(builder);
    }

    /**
     * okhttp 相关配置
     *
     * @param builder OkHttpClient.Builder
     */
    private void handleBuilder(OkHttpClient.Builder builder) {
        //日志打印
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        builder.addInterceptor(loggingInterceptor)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS);//超时设置
    }

    <T> T getService(Class<T> clzz) {
        return new Retrofit.Builder()
                .baseUrl(URLContainer.BASE_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(clzz);
    }
}
