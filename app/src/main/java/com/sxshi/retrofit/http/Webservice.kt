package com.sxshi.retrofit.http

import com.sxshi.retrofit.bean.BaseResponse
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface Webservice {
    /*登录*/
    @POST(URLContainer.LOGIN)
    fun login(@Body requestBody: RequestBody): Observable<BaseResponse<String>>
}