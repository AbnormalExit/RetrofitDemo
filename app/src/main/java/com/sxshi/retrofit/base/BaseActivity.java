package com.sxshi.retrofit.base;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.sxshi.retrofit.model.BaseViewModel;
import com.sxshi.retrofit.utils.ConnectivityUtils;
import com.sxshi.retrofit.utils.DialogUtils;

public abstract class BaseActivity<T extends BaseViewModel> extends AppCompatActivity implements IBaseView {
    protected Dialog progressDialog;

    private HandleException handleException;

    @LayoutRes
    protected abstract int getContentLayoutResId();


    public T model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayoutResId());
        handleException = new HandleException(this);
        if (providerVM() != null) {
            model = ViewModelProviders.of(this).get(providerVM());
        }
        if (model != null) {
            //统一异常处理
            model.getErrorLiveData().observe(this, new Observer<Throwable>() {
                @Override
                public void onChanged(Throwable throwable) {
                    handleException.handlerExcep(throwable);
                }
            });
        }
        initView(savedInstanceState);
        initData();
    }

    protected abstract void initData();

    protected abstract void initView(@Nullable Bundle savedInstanceState);

    /**
     * ViewModel
     *
     * @return ViewModel
     */
    protected abstract Class<T> providerVM();


    @Override
    public void showLoading(String message) {
        progressDialog = DialogUtils.showLoading(this, message);
    }

    @Override
    public void showLoading() {
        progressDialog = DialogUtils.showLoading(this);
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
        progressDialog = null;
    }

    @Override
    public void onUnknownError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTimeout() {
        Toast.makeText(this, "time out", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNetworkError() {
        Toast.makeText(this, "network error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isNetworkConnected() {
        return ConnectivityUtils.isConnectedToInternet(this);
    }

    @Override
    public void onConnectionError() {

    }
}
