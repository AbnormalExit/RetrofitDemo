package com.sxshi.retrofit.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.handpay.mvvm.BaseVM
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : BaseVM() {
    //将所有正在处理的Subscription都添加到CompositeSubscription中。统一退出的时候注销观察
    private var mCompositeDisposable: CompositeDisposable? = CompositeDisposable()
    @JvmField
    protected var repository: DataRepository
    var mutableErrorLiveData = MutableLiveData<Throwable>()
    val errorLiveData: LiveData<Throwable>
        get() = mutableErrorLiveData

    /**
     * 将Disposable添加
     *
     * @param subscription
     */
    fun addDisposable(subscription: Disposable?) { //csb 如果解绑了的话添加 sb 需要新的实例否则绑定时无效的
        if (mCompositeDisposable == null || mCompositeDisposable!!.isDisposed) {
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable!!.add(subscription!!)
    }

    /**
     * 在界面退出等需要解绑观察者的情况下调用此方法统一解绑，防止Rx造成的内存泄漏
     */
    fun unDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable!!.dispose()
        }
    }

    fun clearEvent() {
        if (null != mCompositeDisposable) {
            mCompositeDisposable!!.clear()
        }
    }

    override fun onCleared() {
        super.onCleared()
        unDisposable()
    }

    init {
        repository = DataRepository()
    }
}