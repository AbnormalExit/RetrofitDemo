package com.sxshi.retrofit.ui.main

import android.view.View
import androidx.lifecycle.Observer
import com.sxshi.retrofit.R
import com.sxshi.retrofit.base.BaseFragment
import java.util.*

class MainFragment : BaseFragment<MainViewModel>() {
    override fun initData() {
        model?.userInfoLiveData?.observe(this, Observer { s: String? -> })
    }

    override fun initView(view: View) {
        view.findViewById<View>(R.id.start).setOnClickListener { v: View ->
            val param = HashMap<String, String>()
            model?.login(param)
        }
    }

    override fun providerVM(): Class<MainViewModel>? {
        return MainViewModel::class.java
    }


    override val contentLayoutResId: Int = R.layout.main_fragment

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}