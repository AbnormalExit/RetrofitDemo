package com.sxshi.retrofit.ui.main;

import android.view.View;

import androidx.lifecycle.Observer;

import com.sxshi.retrofit.R;
import com.sxshi.retrofit.base.BaseFragment;

import java.util.HashMap;

public class MainFragment extends BaseFragment<MainViewModel> {
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected void initData() {
        model.getUserInfoLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                // TODO: 2019-12-12  update UI

            }
        });
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> param = new HashMap<>();
                model.Login(param);
            }
        });
    }

    @Override
    protected Class<MainViewModel> providerVM() {
        return MainViewModel.class;
    }

    @Override
    protected int getContentLayoutResId() {
        return R.layout.main_fragment;
    }

}
