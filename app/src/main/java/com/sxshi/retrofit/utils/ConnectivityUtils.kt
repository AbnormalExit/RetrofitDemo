package com.sxshi.retrofit.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * 网络检测工具，需要更新
 */
object ConnectivityUtils {
    fun isConnectedToInternet(context: Context): Boolean {
        val cm = context
                .applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val ni = cm.activeNetworkInfo
        return ni != null && ni.isConnected && ni.isAvailable
    }

    fun isWifiEnabled(context: Context): Boolean {
        val cm = context
                .applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val ni = cm.activeNetworkInfo
        return ni != null && ni.type == ConnectivityManager.TYPE_WIFI
    }

    fun isMobileDataEnabled(context: Context): Boolean {
        val cm = context
                .applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val ni = cm.activeNetworkInfo
        return ni != null && ni.type == ConnectivityManager.TYPE_MOBILE
    }
}