package com.sxshi.retrofit.bean

class BaseResponse<T> {
    var code = 0
    var msg: String? = null
    var data: T? = null
        private set

    fun setData(data: T) {
        this.data = data
    }
}