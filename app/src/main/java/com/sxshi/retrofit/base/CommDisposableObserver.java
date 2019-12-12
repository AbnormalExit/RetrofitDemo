package com.sxshi.retrofit.base;

import androidx.lifecycle.MutableLiveData;

import com.sxshi.retrofit.bean.BaseResponse;

import io.reactivex.observers.DisposableObserver;

/**
 * 统一的网络请求处理。
 * 统一错误处理
 *
 * @param <T>
 */
public abstract class CommDisposableObserver<T extends BaseResponse> extends DisposableObserver<T> {

    /**
     * 全局错误处理
     */
    private MutableLiveData<Throwable> errorLiveData;

    public CommDisposableObserver(MutableLiveData<Throwable> errorLiveData) {
        this.errorLiveData = errorLiveData;
    }

    @Override
    public void onNext(T t) {
        //http 200..300都会进入此方法，如果需要自定义成功状态码这需要在这里添加
        onSuccess(t);
    }

    @Override
    public void onError(Throwable t) {
        errorLiveData.setValue(t);
    }

    @Override
    public void onComplete() {

    }

    protected abstract void onSuccess(T t);
}