package com.congda.jinxinkt.app.utils

import com.jess.arms.mvp.IView
import com.jess.arms.utils.RxLifecycleUtils
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RxUtils {
    fun <T> applySchedulers(view: IView): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    view.showLoading() //显示进度条
                }
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally {
                    view.hideLoading() //隐藏进度条
                }.compose(RxLifecycleUtils.bindToLifecycle(view))
        }
    }
}