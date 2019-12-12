package com.sxshi.retrofit.http;


import com.sxshi.retrofit.bean.BaseResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Webservice {

    /*登录*/
    @POST(URLContainer.LOGIN)
    Observable<BaseResponse<String>> login(@Body RequestBody requestBody);
}
