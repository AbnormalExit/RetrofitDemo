package com.sxshi.retrofit.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sxshi.retrofit.base.CommDisposableObserver
import com.sxshi.retrofit.bean.BaseResponse
import com.sxshi.retrofit.model.BaseViewModel
import java.util.*

class MainViewModel : BaseViewModel() {
    private val mutableLiveData = MutableLiveData<String>()
    val userInfoLiveData: LiveData<String>
        get() = mutableLiveData

    fun login(param: HashMap<String, String>) {
        repository.login(param, object : CommDisposableObserver<BaseResponse<String>>(this) {
            override fun onSuccess(t: BaseResponse<String>) {
                mutableLiveData.value = t.data
            }
        })
    }
}