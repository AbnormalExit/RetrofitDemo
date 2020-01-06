package com.sxshi.retrofit.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sxshi.retrofit.base.CommDisposableObserver;
import com.sxshi.retrofit.bean.BaseResponse;
import com.sxshi.retrofit.model.BaseViewModel;

import java.util.HashMap;

public class MainViewModel extends BaseViewModel {

    private MutableLiveData<String> mutableLiveData = new MutableLiveData<>();


    public LiveData<String> getUserInfoLiveData() {
        return mutableLiveData;
    }

    public void Login(HashMap<String, String> param) {
        repository.login(param, new CommDisposableObserver<BaseResponse<String>>(this) {
            @Override
            protected void onSuccess(BaseResponse<String> stringBaseResponse) {
                mutableLiveData.setValue(stringBaseResponse.getData());
            }
        });
    }


}
