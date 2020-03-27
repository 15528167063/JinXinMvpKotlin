package com.congda.baselibrary.loading

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.text.TextUtils
import com.congda.baselibrary.R

object ShowLoadiongUtils {
    var pd: ProgressDialog?=null
    var loadingDialog: DialogMessageTypeOne?=null
    var dialogMessage: DialogMessageTypeTwo?=null
    var progressDialog: DialogMessageTypeTwo?=null
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
        if (pd!=null && pd!!.isShowing) {
            pd!!.dismiss()
            pd=null
        }
        pd = ProgressDialog(context)
        pd!!.setMessage(context.resources.getString(R.string.im_loading))
        pd!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        pd!!.setCancelable(true)
        pd!!.show()
    }
    fun dissloadingTypeZero() {
        if (pd!=null &&  pd!!.isShowing) {
            pd!!.dismiss()
            pd=null
        }
    }
    /**
     * 显示加载界面loading(第1种自定义)
     */
    fun showLoadingDialogTypeOne(context: Context) {
        if (loadingDialog!=null && loadingDialog!!.isshow()) {
            loadingDialog!!.dismiss()
            loadingDialog=null
        }
        loadingDialog = DialogMessageTypeOne(context)
        loadingDialog!!.set_progress(context.resources.getString(R.string.im_loading))
        loadingDialog!!.show()
    }

    fun dismissLoadingDialogTypeOne() {
        if (loadingDialog!=null &&  loadingDialog!!.isshow()) {
            loadingDialog!!.dismiss()
            loadingDialog=null
        }
    }
    /**
     * 显示加载界面loading(第2种自定义)
     */
    fun showLoadingDialogTypeTwo(context: Context, msg: String) {
        if (dialogMessage!=null ) {
            dialogMessage!!.dismiss()
            dialogMessage=null
        }
        dialogMessage = DialogMessageTypeTwo(context)
        dialogMessage!!.setType(1)
        if (!TextUtils.isEmpty(msg)) {
            dialogMessage!!.setMessage(msg)
        }
        dialogMessage!!.showDialog()
    }

    fun dismissLoadingDialogTypeTwo() {
        if (dialogMessage!=null) {
            dialogMessage!!.dissmissDialog()
            dialogMessage=null
        }
    }

    /**
     * 显示加载界面loading(带进度条)
     */
    fun showLoadingDialogProgress(context: Context, progeress: Int) {
        if(progressDialog==null){
            progressDialog = DialogMessageTypeTwo(context)
            progressDialog!!.setType(1)
            progressDialog!!.showDialog()
        }
        if (!progressDialog!!.isShowing) {
            progressDialog!!.showDialog()
        }
        progressDialog!!.setProgress(progeress)
    }

    fun dismissProgress() {
        if (progressDialog!=null) {
            progressDialog!!.dissmissDialog()
            progressDialog=null
        }
    }
}