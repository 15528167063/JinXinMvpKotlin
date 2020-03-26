package com.congda.baselibrary.loading

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.text.TextUtils
import com.congda.baselibrary.R

object ShowLoadiongUtils {
    lateinit var pd: ProgressDialog
    lateinit var loadingDialog: DialogMessageTypeOne
    lateinit var dialogMessage: DialogMessageTypeTwo
    lateinit var progressDialog: DialogMessageTypeTwo
    /**
     * 通用Dialog弹窗
     */
    fun showDialog(context: Context, title: String, message: String, listener: DialogInterface.OnClickListener) {
        val normalDialog = AlertDialog.Builder(context)
        normalDialog.setTitle(title)
        normalDialog.setMessage(message)
        normalDialog.setPositiveButton("确定", listener)
        normalDialog.setNegativeButton("取消") { dialog, which -> dialog.dismiss() }
        // 显示
        normalDialog.show()
    }
    /**
     * 显示加载界面loading(自带)
     */
    fun showLoadingTypeZero(context: Context) {
        if (this::pd.isInitialized && pd.isShowing) {
            pd.dismiss()
        }
        if (!this::loadingDialog.isInitialized) {
            pd = ProgressDialog(context)
            pd.setMessage(context.resources.getString(R.string.im_loading))
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            pd.setCancelable(true)
        }
        pd.show()
    }

    fun dissloadingTypeZero() {
        if (this::pd.isInitialized && pd.isShowing) {
            pd.dismiss()
        }
    }
    /**
     * 显示加载界面loading(第1种自定义)
     */
    fun showLoadingDialogTypeOne(context: Context) {
        if (!this::loadingDialog.isInitialized) {
            loadingDialog = DialogMessageTypeOne(context)
            loadingDialog.set_progress(context.resources.getString(R.string.im_loading))
        }
        loadingDialog.show()
    }

    fun dismissLoadingDialogTypeOne() {
        if (this::loadingDialog.isInitialized) {
            loadingDialog.dismiss()
        }
    }
    /**
     * 显示加载界面loading(第2种自定义)
     */
    fun showLoadingDialogTypeTwo(context: Context, msg: String) {
        if (!this::dialogMessage.isInitialized) {
            dialogMessage = DialogMessageTypeTwo(context)
            dialogMessage.setType(1)
        }
        if (!TextUtils.isEmpty(msg)) {
            dialogMessage.setMessage(msg)
        }
        dialogMessage.showDialog()
    }

    fun dismissLoadingDialogTypeTwo() {
        if (this::dialogMessage.isInitialized) {
            dialogMessage.dissmissDialog()
        }
    }

    /**
     * 显示加载界面loading(带进度条)
     */
    fun showLoadingDialogProgress(context: Context, progeress: Int) {
        if (!this::progressDialog.isInitialized) {
            progressDialog = DialogMessageTypeTwo(context)
            progressDialog.setType(1)
            progressDialog.showDialog()
        }
        if (!progressDialog.isShowing) {
            progressDialog.showDialog()
        }
        progressDialog.setProgress(progeress)
    }

    fun dismissProgress() {
        if (this::progressDialog.isInitialized) {
            progressDialog.dissmissDialog()
        }
    }
}