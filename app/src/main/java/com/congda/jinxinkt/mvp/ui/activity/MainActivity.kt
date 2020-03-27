package com.congda.jinxinkt.mvp.ui.activity

import android.os.Bundle
import com.congda.jinxinkt.R
import com.congda.jinxinkt.di.component.DaggerMainComponent
import com.congda.jinxinkt.di.module.MainModule
import com.congda.jinxinkt.mvp.contract.MainContract
import com.congda.jinxinkt.mvp.presenter.MainPresenter
import com.congda.jinxinkt.mvp.ui.IMBaseActivity
import com.congda.jinxinkt.utils.glide.IMImageLoadUtil
import com.jess.arms.di.component.AppComponent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : IMBaseActivity<MainPresenter>(), MainContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .mainModule(MainModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        IMImageLoadUtil.CommonImageLineCircleLoad(this,"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1585278442&di=5832efb94684bbc35677a7ed0b374d7a&src=http://a3.att.hudong.com/68/61/300000839764127060614318218_950.jpg",iv1)
        btn1.setOnClickListener{
            showLoading()
            btn1.postDelayed({hideLoading()},2000)
        }
    }
}
