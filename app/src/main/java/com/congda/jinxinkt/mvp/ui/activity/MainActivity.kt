package com.congda.jinxinkt.mvp.ui.activity

import android.os.Bundle
import com.congda.jinxinkt.R
import com.congda.jinxinkt.di.component.DaggerMainComponent
import com.congda.jinxinkt.di.module.MainModule
import com.congda.jinxinkt.mvp.contract.MainContract
import com.congda.jinxinkt.mvp.presenter.MainPresenter
import com.congda.jinxinkt.mvp.ui.IMBaseActivity
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
        btn1.setOnClickListener{
            showLoading()
            btn1.postDelayed({hideLoading()},2000)
        }
    }
}
