package com.handpay.mvvm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public abstract class BaseVMFragment<VM extends BaseVM> extends Fragment {

    protected VM vm;

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
            vm = ViewModelProviders.of(this).get(providerVM());
        initData();
    }


    protected abstract void initData();

    protected abstract void initView(View view);

    /**
     * ViewModel
     *
     * @return ViewModel
     */
    protected abstract Class<VM> providerVM();

    /**
     * @return layoutId
     */
    @LayoutRes
    protected abstract int getContentLayoutResId();
}

