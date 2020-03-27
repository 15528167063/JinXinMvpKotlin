package com.congda.jinxinkt.mvp.presenter

import android.app.Application
import android.widget.CheckedTextView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.congda.baselibrary.widget.ViewPagerSlide
import com.congda.jinxinkt.adapter.MyViewPagerAdapter
import com.congda.jinxinkt.mvp.contract.MainContract
import com.congda.jinxinkt.mvp.ui.fragment.HomeFragment
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import kotlinx.android.synthetic.main.activity_main.*
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 03/26/2020 09:57
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class MainPresenter
@Inject
constructor(model: MainContract.Model, rootView: MainContract.View) :
    BasePresenter<MainContract.Model, MainContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager


    override fun onDestroy() {
        super.onDestroy();
    }


    fun setdata(viewpager: ViewPagerSlide, mFragments: MutableList<Fragment>) {
        viewpager.isCanScrollble = true
        viewpager.offscreenPageLimit = 3
        initFragment(mFragments)
        mRootView.setViewPager(mFragments)

    }

    fun initFragment(mFragments: MutableList<Fragment>) {
        mFragments.add( HomeFragment.newInstance())
        mFragments.add(HomeFragment.newInstance())
        mFragments.add(HomeFragment.newInstance())
        mFragments.add(HomeFragment.newInstance())
    }
}
