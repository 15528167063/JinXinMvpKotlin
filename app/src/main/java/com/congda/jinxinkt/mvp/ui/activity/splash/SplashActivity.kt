package com.congda.jinxinkt.mvp.ui.activity.splash

import android.content.Intent
import android.os.Bundle
import com.congda.baselibrary.imutils.IMCutTimeDownView
import com.congda.baselibrary.imutils.IMStatusBarUtil
import com.congda.jinxinkt.R
import com.congda.jinxinkt.di.component.splash.DaggerSplashComponent
import com.congda.jinxinkt.di.module.splash.SplashModule
import com.congda.jinxinkt.mvp.contract.splash.SplashContract
import com.congda.jinxinkt.mvp.model.entity.SplashAdBean
import com.congda.jinxinkt.mvp.presenter.splash.SplashPresenter
import com.congda.jinxinkt.mvp.ui.IMBaseActivity
import com.congda.jinxinkt.mvp.ui.activity.MainActivity
import com.congda.jinxinkt.utils.glide.IMImageLoadUtil
import com.jess.arms.di.component.AppComponent
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : IMBaseActivity<SplashPresenter>(), SplashContract.View,
    IMCutTimeDownView.OnFinishListener {


    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerSplashComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .splashModule(SplashModule(this))
            .build()
            .inject(this)
    }

    override fun initStatusBar() {
        IMStatusBarUtil.setTranslucentForImageView(this, 0, null)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_splash //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        skipTv.setOnFinishListener(this)
        mPresenter?. CheckedVersion(this)
    }

    override fun setOnFinishListener() {
        val intent = Intent(this, GuideActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun handleView(s: List<SplashAdBean>) {
        skipTv.setTotalTime(3000)
        IMImageLoadUtil.CommonSplashImageLoadCp(this, s[0].adsImgUrl, iv_bg)
    }
}

