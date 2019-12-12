package com.sxshi.retrofit.base;

import com.sxshi.retrofit.http.Webservice;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BaseRepository {

    protected Webservice services;

    public BaseRepository() {
        BaseRetrofit retrofit = new BaseRetrofit();
        this.services = retrofit.getService(Webservice.class);
    }


    protected <T> void addObserver(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }
}
