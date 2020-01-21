package com.sxshi.retrofit.base

import com.sxshi.retrofit.http.Webservice
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class BaseRepository {
    @JvmField
    protected var services: Webservice

    protected fun <T> addObserver(observable: Observable<T>, observer: Observer<T>?) {
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread()) //子线程访问网络
                .observeOn(AndroidSchedulers.mainThread()) //回调到主线程
                .subscribe(observer!!)
    }

    init {
        val retrofit = BaseRetrofit()
        services = retrofit.getService(Webservice::class.java)
    }
}