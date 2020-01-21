package com.sxshi.retrofit.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.handpay.mvvm.BaseVMFragment
import com.sxshi.retrofit.model.BaseViewModel

abstract class BaseFragment<T : BaseViewModel> : BaseVMFragment<T>(), IBaseView {
    private var mActivity: BaseActivity<*>? = null
    private var handleException: HandleException? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            mActivity = context
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        handleException = HandleException(this)
        model?.errorLiveData?.observe(this, Observer { throwable: Throwable -> handleException!!.handlerExcep(throwable) })
    }

    override fun showLoading(message: String?) {
        mActivity?.showLoading(message)
    }


    override fun hideLoading() {
        mActivity?.hideLoading()
    }

    override fun onUnknownError(error: String?) {
        mActivity?.onUnknownError(error)
    }

    override fun onTimeout() {
        mActivity?.onTimeout()
    }

    override fun onNetworkError() {

        mActivity?.onNetworkError()

    }


    override fun onConnectionError() {
        mActivity?.onConnectionError()
    }
}