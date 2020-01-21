package com.sxshi.retrofit.model

import com.sxshi.retrofit.base.BaseRepository
import com.sxshi.retrofit.bean.BaseResponse
import com.sxshi.retrofit.utils.ParamUtils
import io.reactivex.Observer
import java.util.*

class DataRepository : BaseRepository() {
    /**
     * 登录
     *
     * @param param    参数
     * @param observer observer
     * @return Observable<BaseResponse></BaseResponse> < String>>
     */
    fun login(param: HashMap<String, String>, observer: Observer<BaseResponse<String>>) {
        addObserver(services.login(ParamUtils.getRequestBody(param)), observer)
    }
}