package com.congda.baselibrary.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class ViewPagerSlide : ViewPager {
    var isCanScrollble = false

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return if (!isCanScrollble) {
            false
        } else super.onTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return if (!isCanScrollble) {
            false
        } else super.onInterceptTouchEvent(ev)
    }

}