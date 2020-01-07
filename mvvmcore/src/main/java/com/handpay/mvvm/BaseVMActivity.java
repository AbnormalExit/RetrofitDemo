package com.handpay.mvvm;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public abstract class BaseVMActivity<VM extends BaseVM> extends AppCompatActivity {

    public VM vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayoutResId());
        if (providerVM() != null) {
            vm = ViewModelProviders.of(this).get(providerVM());
        }
        initView(savedInstanceState);
        initData();
    }

    protected abstract Class<VM> providerVM();

    protected abstract void initData();

    protected abstract void initView(@Nullable Bundle savedInstanceState);

    @LayoutRes
    protected abstract int getContentLayoutResId();

}
