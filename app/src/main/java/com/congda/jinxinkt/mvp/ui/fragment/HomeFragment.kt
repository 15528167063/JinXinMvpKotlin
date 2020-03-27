package com.congda.jinxinkt.mvp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.congda.jinxinkt.R
import com.congda.jinxinkt.di.component.fragment.DaggerHomeComponent
import com.congda.jinxinkt.di.module.fragment.HomeModule
import com.congda.jinxinkt.mvp.contract.fragment.HomeContract
import com.congda.jinxinkt.mvp.presenter.fragment.HomePresenter
import com.congda.jinxinkt.mvp.ui.IMBaseFragment
import com.jess.arms.di.component.AppComponent

class HomeFragment : IMBaseFragment<HomePresenter>(), HomeContract.View {
    companion object {
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            return fragment
        }
    }


    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerHomeComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .homeModule(HomeModule(this))
            .build()
            .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun setData(data: Any?) {

    }

}
