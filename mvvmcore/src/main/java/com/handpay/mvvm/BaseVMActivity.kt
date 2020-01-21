package com.handpay.mvvm

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

abstract class BaseVMActivity<VM : BaseVM?> : AppCompatActivity() {
    var model: VM? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentLayoutResId)
        providerVM()?.let {
            model=ViewModelProviders.of(this).get(it)
        }
        initView(savedInstanceState)
        initData()
    }

    protected fun providerVM(): Class<VM>? = null
    protected abstract fun initData()
    protected abstract fun initView(savedInstanceState: Bundle?)
    @get:LayoutRes
    protected abstract val contentLayoutResId: Int
}