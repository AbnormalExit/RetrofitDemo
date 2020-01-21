package com.sxshi.retrofit.utils

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.sxshi.retrofit.R

/**
 * 对话框工具类
 */
class DialogUtils private constructor() {
    companion object {
        fun showLoading(context: Context): ProgressDialog {
            return showLoading(context, "", false)
        }

        fun showLoading(context: Context, isCancelable: Boolean): ProgressDialog {
            return showLoading(context, "", isCancelable)
        }

        fun showLoading(context: Context, message: String?): ProgressDialog {
            return showLoading(context, message, false)
        }

        fun showLoading(context: Context, @StringRes message: Int): ProgressDialog {
            return showLoading(context, context.getString(message), false)
        }

        fun showLoading(context: Context,
                        @StringRes message: Int,
                        isCancelable: Boolean): ProgressDialog {
            return showLoading(context, context.getString(message), isCancelable)
        }

        private fun showLoading(context: Context,
                                message: String?,
                                isCancelable: Boolean): ProgressDialog {
            val progressDialog = ProgressDialog(context)
            progressDialog.show()
            val window = progressDialog.window
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.view_loading, null)
            val messageTextView = view.findViewById<TextView>(R.id.loading_message)
            if (message.isNullOrEmpty()) {
                messageTextView.visibility = View.GONE
            } else {
                messageTextView.text = message
                messageTextView.visibility = View.VISIBLE
            }
            progressDialog.setContentView(view)
            progressDialog.isIndeterminate = true
            progressDialog.setCancelable(isCancelable)
            progressDialog.setCanceledOnTouchOutside(isCancelable)
            return progressDialog
        }

        fun getAlertDialog(context: Context,
                           message: String): AlertDialog.Builder {
            return getAlertDialog(context, "", message, false)
        }

        fun getAlertDialog(context: Context,
                           @StringRes message: Int): AlertDialog.Builder {
            return getAlertDialog(context, "", context.getString(message), false)
        }

        fun getAlertDialog(context: Context,
                           message: String,
                           isCancelable: Boolean): AlertDialog.Builder {
            return getAlertDialog(context, "", message, isCancelable)
        }

        fun getAlertDialog(context: Context,
                           @StringRes message: Int,
                           isCancelable: Boolean): AlertDialog.Builder {
            return getAlertDialog(context, "", context.getString(message), isCancelable)
        }

        fun getAlertDialog(context: Context,
                           @StringRes title: Int,
                           @StringRes message: Int,
                           isCancelable: Boolean): AlertDialog.Builder {
            return getAlertDialog(context,
                    context.getString(title),
                    context.getString(message),
                    isCancelable)
        }

        private fun getAlertDialog(context: Context,
                                   title: String,
                                   message: String,
                                   isCancelable: Boolean): AlertDialog.Builder {
            val builder = AlertDialog.Builder(context)
            if (!TextUtils.isEmpty(title)) {
                builder.setTitle(title)
            }
            builder.setMessage(message)
            builder.setCancelable(isCancelable)
            return builder
        }
    }

    init {
        throw AssertionError()
    }
}