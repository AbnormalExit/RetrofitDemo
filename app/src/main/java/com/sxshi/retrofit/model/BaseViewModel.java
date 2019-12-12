package com.sxshi.retrofit.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseViewModel extends ViewModel {

    //将所有正在处理的Subscription都添加到CompositeSubscription中。统一退出的时候注销观察
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    protected DataRepository repository;


    protected MutableLiveData<Throwable> mutableErrorLiveData = new MutableLiveData<>();

    private LiveData<Throwable> errorLiveData;

    public LiveData<Throwable> getErrorLiveData() {
        return mutableErrorLiveData;
    }

    public BaseViewModel() {
        repository = new DataRepository();
    }

    /**
     * 将Disposable添加
     *
     * @param subscription
     */
    public void addDisposable(Disposable subscription) {
        //csb 如果解绑了的话添加 sb 需要新的实例否则绑定时无效的
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    /**
     * 在界面退出等需要解绑观察者的情况下调用此方法统一解绑，防止Rx造成的内存泄漏
     */
    public void unDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

    public void clearEvent() {
        if (null != mCompositeDisposable) {
            mCompositeDisposable.clear();
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        unDisposable();
    }
}
