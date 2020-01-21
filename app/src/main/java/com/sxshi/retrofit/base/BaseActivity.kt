package com.sxshi.retrofit.base

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.handpay.mvvm.BaseVMActivity
import com.sxshi.retrofit.model.BaseViewModel
import com.sxshi.retrofit.utils.DialogUtils

abstract class BaseActivity<T : BaseViewModel?> : BaseVMActivity<T>(), IBaseView {
    protected var progressDialog: Dialog? = null
    private var handleException: HandleException? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleException = HandleException(this)
        model?.errorLiveData?.observe(this, Observer { throwable -> handleException!!.handlerExcep(throwable) })
    }

    override fun hideLoading() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.cancel()
        }
        progressDialog = null
    }

    override fun showLoading(message: String?) {
        progressDialog = DialogUtils.showLoading(this, message)

    }

    override fun onUnknownError(message: String?) {
        progressDialog = DialogUtils.showLoading(this, message)
    }

    override fun onTimeout() {
        Toast.makeText(this, "time out", Toast.LENGTH_SHORT).show()
    }

    override fun onNetworkError() {
        Toast.makeText(this, "network error", Toast.LENGTH_SHORT).show()
    }

    override fun onConnectionError() {
        Toast.makeText(this, "connection error", Toast.LENGTH_SHORT).show()
    }
}