package com.handpay.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

abstract class BaseVMFragment<VM : BaseVM?> : Fragment() {
    protected var model: VM? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(contentLayoutResId, container, false)
        providerVM()?.let {
            model= ViewModelProviders.of(this).get(it)
        }
        initView(rootView)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }

    protected abstract fun initData()
    protected abstract fun initView(view: View)
    /**
     * ViewModel
     *
     * @return ViewModel
     */
    open fun providerVM(): Class<VM>? = null

    /**
     * @return layoutId
     */
    @get:LayoutRes
    protected abstract val contentLayoutResId: Int
}