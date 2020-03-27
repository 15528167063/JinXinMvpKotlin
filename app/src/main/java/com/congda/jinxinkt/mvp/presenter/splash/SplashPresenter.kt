package com.congda.jinxinkt.mvp.presenter.splash

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import com.congda.baselibrary.imutils.IMPreferenceUtil
import com.congda.jinxinkt.app.utils.RxUtils
import com.congda.jinxinkt.application.Constanst
import com.congda.jinxinkt.mvp.contract.splash.SplashContract
import com.congda.jinxinkt.mvp.model.entity.IMHttpResult
import com.congda.jinxinkt.mvp.model.entity.SplashAdBean
import com.congda.jinxinkt.mvp.model.entity.VersonBeanData
import com.congda.jinxinkt.mvp.ui.activity.splash.GuideActivity
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject


@ActivityScope
class SplashPresenter
@Inject
constructor(model: SplashContract.Model, rootView: SplashContract.View) : BasePresenter<SplashContract.Model, SplashContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager


    private var versionCode :Int=0 // 版本号

    private var versionName : String? = null// 版本名

    override fun onDestroy() {
        super.onDestroy();
    }
    fun CheckedVersion(context: Context) {
        mModel.CheckedVersion("1")
            .subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( object : ErrorHandleSubscriber<IMHttpResult<VersonBeanData>>(mErrorHandler) {
                override fun onNext(t: IMHttpResult<VersonBeanData>) {
                    getSplashData()
                }
            });
    }

    fun getSplashData() {
        mModel.getGetAdJson()
            .compose(RxUtils.applySchedulers(mRootView))
            .subscribe( object : ErrorHandleSubscriber<IMHttpResult<List<SplashAdBean>>>(mErrorHandler) {
                override fun onNext(t: IMHttpResult<List<SplashAdBean>>) {
                    mRootView.handleView(t.data)
                }
            });
    }
}
