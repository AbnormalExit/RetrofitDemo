package com.sxshi.retrofit.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.sxshi.retrofit.model.BaseViewModel;

public abstract class BaseFragment<T extends BaseViewModel> extends Fragment implements IBaseView {
    private BaseActivity mActivity;

    public T model;

    private HandleException handleException;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.mActivity = (BaseActivity) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getContentLayoutResId(), container, false);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (providerVM() != null)
            model = ViewModelProviders.of(this).get(providerVM());
        handleException = new HandleException(this);
        if (model != null) {
            //统一异常处理
            model.getErrorLiveData().observe(this, new Observer<Throwable>() {
                @Override
                public void onChanged(Throwable throwable) {
                    handleException.handlerExcep(throwable);
                }
            });
        }
        initData();
    }

    protected abstract void initData();

    protected abstract void initView(View view);

    /**
     * ViewModel
     *
     * @return ViewModel
     */
    protected abstract Class<T> providerVM();

    /**
     * @return layoutId
     */
    @LayoutRes
    protected abstract int getContentLayoutResId();


    @Override
    public void showLoading(String message) {
        if (mActivity != null) {
            mActivity.showLoading(message);
        }
    }

    @Override
    public void showLoading() {
        if (mActivity != null) {
            mActivity.showLoading();
        }
    }

    @Override
    public void hideLoading() {
        if (mActivity != null) {
            mActivity.hideLoading();
        }
    }

    @Override
    public void onUnknownError(String error) {
        if (mActivity != null) {
            mActivity.onUnknownError(error);
        }
    }

    @Override
    public void onTimeout() {
        if (mActivity != null) {
            mActivity.onTimeout();
        }
    }

    @Override
    public void onNetworkError() {
        if (mActivity != null) {
            mActivity.onNetworkError();
        }
    }

    @Override
    public boolean isNetworkConnected() {
        if (mActivity != null) {
            return mActivity.isNetworkConnected();
        }
        return false;
    }

    @Override
    public void onConnectionError() {
        if (mActivity != null) {
            mActivity.onConnectionError();
        }
    }
}
