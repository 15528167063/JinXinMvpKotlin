package com.congda.jinxinkt.app.utils

import com.jess.arms.mvp.IView
import com.jess.arms.utils.RxLifecycleUtils
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * ================================================
 * 放置便于使用 RxJava 的一些工具方法
 *
 *
 * Created by MVPArmsTemplate on 03/26/2020 09:57
 * [Contact me](mailto:jess.yan.effort@gmail.com)
 * [Follow me](https://github.com/JessYanCoding)
 * ================================================
 */
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