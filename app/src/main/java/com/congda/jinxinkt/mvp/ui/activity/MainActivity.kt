package com.congda.jinxinkt.mvp.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.CheckedTextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.congda.baselibrary.imutils.IMStatusBarUtil
import com.congda.jinxinkt.R
import com.congda.jinxinkt.adapter.MyViewPagerAdapter
import com.congda.jinxinkt.di.component.DaggerMainComponent
import com.congda.jinxinkt.di.module.MainModule
import com.congda.jinxinkt.mvp.contract.MainContract
import com.congda.jinxinkt.mvp.presenter.MainPresenter
import com.congda.jinxinkt.mvp.ui.IMBaseActivity
import com.congda.jinxinkt.mvp.ui.fragment.HomeFragment
import com.jess.arms.di.component.AppComponent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : IMBaseActivity<MainPresenter>(), MainContract.View, ViewPager.OnPageChangeListener, View.OnClickListener {
    var mLastIndex = 0
    var mFragments: MutableList<Fragment> = mutableListOf()
    lateinit var mAdapter: MyViewPagerAdapter
    lateinit var mCheckedTextViews: Array<CheckedTextView?>

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .mainModule(MainModule(this))
            .build()
            .inject(this)
    }

    override fun initStatusBar() {
        IMStatusBarUtil.setTranslucentForImageViewInFragment(this, 0, null)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }
    override fun initData(savedInstanceState: Bundle?) {
        setSwipeBackEnable(false)
        mPresenter!!.setdata(viewpager,mFragments)
    }

    override fun setViewPager( mFragments: MutableList<Fragment>) {
        mAdapter = MyViewPagerAdapter(getSupportFragmentManager(), mFragments)
        viewpager.setAdapter(mAdapter)
        viewpager.addOnPageChangeListener(this)
        initTabs()
    }

    private fun initTabs() {
        mCheckedTextViews = arrayOfNulls<CheckedTextView>(tabs.childCount)
        for (i in 0 until tabs.childCount) {
            mCheckedTextViews[i] = tabs.getChildAt(i) as CheckedTextView
            mCheckedTextViews[i]?.setOnClickListener(this)
        }
    }
    override fun onPageScrollStateChanged(state: Int) {
    }
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }
    override fun onPageSelected(position: Int) {
        if (position == mLastIndex) {
            return
        }
        mCheckedTextViews[position]!!.isChecked = true
        mCheckedTextViews[mLastIndex]!!.isChecked = false
        mLastIndex = position
    }
    override fun onClick(v: View?) {
        for (i in mCheckedTextViews.indices) {
            if (v === mCheckedTextViews[i]) {
                if (i == mLastIndex) {
                    break
                }
                mCheckedTextViews[i]!!.isChecked = true
                mCheckedTextViews[mLastIndex]!!.isChecked = false
                mLastIndex = i
                viewpager.setCurrentItem(i, false)
                break
            }
        }
    }
}
