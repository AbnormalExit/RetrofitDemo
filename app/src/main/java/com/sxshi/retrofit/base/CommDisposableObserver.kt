package com.sxshi.retrofit.base

import androidx.lifecycle.MutableLiveData
import com.sxshi.retrofit.bean.BaseResponse
import com.sxshi.retrofit.model.BaseViewModel
import io.reactivex.observers.DisposableObserver
import java.lang.ref.WeakReference

/**
 * 统一的网络请求处理。
 * 统一错误处理
 *
 * @param <T>
</T> */
abstract class CommDisposableObserver<T : BaseResponse<*>>(model: BaseViewModel) : DisposableObserver<T>() {
    /**
     * 全局错误处理
     */
    private val weakReference: WeakReference<BaseViewModel> = WeakReference(model)
    private val currModel: BaseViewModel?

    override fun onNext(t: T) { //http 200..300都会进入此方法，如果需要自定义成功状态码这需要在这里添加
        onSuccess(t)
    }

    override fun onError(t: Throwable) {
        currModel?.mutableErrorLiveData?.value = t
    }

    override fun onComplete() {}
    protected abstract fun onSuccess(t: T)

    init {
        currModel = weakReference.get()
        currModel?.addDisposable(this)
    }
}