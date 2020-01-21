package com.sxshi.retrofit

import android.os.Bundle
import com.sxshi.retrofit.base.BaseActivity
import com.sxshi.retrofit.model.BaseViewModel
import com.sxshi.retrofit.ui.main.MainFragment.Companion.newInstance
import com.sxshi.retrofit.utils.FragmentTransactionUtil.Companion.addFragment

class MainActivity : BaseActivity<BaseViewModel>() {
    override val contentLayoutResId = R.layout.main_activity
    override fun initData() {}
    override fun initView(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            addFragment(supportFragmentManager, newInstance(), R.id.container, false)
        }
    }
}