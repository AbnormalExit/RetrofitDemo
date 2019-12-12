package com.sxshi.retrofit;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.sxshi.retrofit.base.BaseActivity;
import com.sxshi.retrofit.ui.main.MainFragment;
import com.sxshi.retrofit.utils.FragmentTransactionUtil;

public class MainActivity extends BaseActivity {

    @Override
    protected int getContentLayoutResId() {
        return R.layout.main_activity;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            FragmentTransactionUtil.addFragment(getSupportFragmentManager(), MainFragment.newInstance(), R.id.container, false);
        }
    }

    @Override
    protected Class providerVM() {
        return null;
    }
}
