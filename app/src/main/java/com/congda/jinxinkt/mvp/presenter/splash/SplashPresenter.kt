package com.congda.jinxinkt.mvp.presenter.splash

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import com.congda.jinxinkt.app.utils.RxUtils
import com.congda.jinxinkt.mvp.contract.splash.SplashContract
import com.congda.jinxinkt.mvp.model.entity.IMHttpResult
import com.congda.jinxinkt.mvp.model.entity.SplashAdBean
import com.congda.jinxinkt.mvp.model.entity.VersonBeanData
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.utils.RxLifecycleUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 03/26/2020 16:09
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
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


    private var versionCode // 版本号
            = 0
    private var versionName : String? = null// 版本名

    override fun onDestroy() {
        super.onDestroy();
    }

    fun CheckedVersion(context: Context) {
        // 获取自己的版本信息
        getMyVersion(context)
        mModel.CheckedVersion("1")
            .subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
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

    private fun getMyVersion(context: Context) {
        val pm = context.packageManager
        try {
            val packageInfo = pm.getPackageInfo(context.packageName, 0)
            // 版本号
            versionCode = packageInfo.versionCode
            // 版本名
            versionName = packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            return
        }
    }

}
