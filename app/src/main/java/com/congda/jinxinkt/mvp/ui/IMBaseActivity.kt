package com.congda.jinxinkt.mvp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.InflateException
import android.view.View
import butterknife.ButterKnife
import butterknife.Unbinder
import com.congda.baselibrary.base.BaseActivity
import com.congda.baselibrary.loading.ShowLoadiongUtils
import com.congda.baselibrary.loading.ShowLoadiongUtils.dismissLoadingDialogTypeOne
import com.congda.baselibrary.loading.ShowLoadiongUtils.dismissLoadingDialogTypeTwo
import com.congda.baselibrary.loading.ShowLoadiongUtils.dissloadingTypeZero
import com.congda.baselibrary.loading.ShowLoadiongUtils.showLoadingDialogTypeOne
import com.congda.baselibrary.loading.ShowLoadiongUtils.showLoadingDialogTypeTwo
import com.congda.jinxinkt.R
import com.jess.arms.base.delegate.IActivity
import com.jess.arms.integration.cache.Cache
import com.jess.arms.integration.cache.CacheType
import com.jess.arms.integration.lifecycle.ActivityLifecycleable
import com.jess.arms.mvp.IPresenter
import com.jess.arms.mvp.IView
import com.jess.arms.utils.ArmsUtils
import com.jess.arms.utils.Preconditions
import com.jess.arms.utils.ThirdViewUtil
import com.trello.rxlifecycle2.android.ActivityEvent
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import javax.inject.Inject
abstract class IMBaseActivity<P : IPresenter?> : BaseActivity(), IActivity, ActivityLifecycleable, IView {
    protected val TAG = this.javaClass.simpleName
    private val mLifecycleSubject = BehaviorSubject.create<ActivityEvent>()
    private var mCache: Cache<Any, Any>? = null
    private var mUnbinder: Unbinder? = null
    @JvmField
    @Inject
    var mPresenter: P? = null //如果当前页面逻辑简单, Presenter 可以为 nul

    @Synchronized
    override fun provideCache(): Cache<String, Any> {
        if (mCache == null) {
            mCache = ArmsUtils.obtainAppComponentFromContext(this).cacheFactory().build(CacheType.ACTIVITY_CACHE)
        }
        return mCache as Cache<String, Any>
    }

    override fun provideLifecycleSubject(): Subject<ActivityEvent> {
        return mLifecycleSubject
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        val view = ThirdViewUtil.convertAutoView(name, context, attrs)
        return view ?: super.onCreateView(name, context, attrs)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            val layoutResID = initView(savedInstanceState)
            //如果initView返回0,框架则不会调用setContentView(),当然也不会 Bind ButterKnife
            if (layoutResID != 0) {
                setContentView(layoutResID)
                //绑定到butterknife
                mUnbinder = ButterKnife.bind(this)
            }
        } catch (e: Exception) {
            if (e is InflateException) throw e
            e.printStackTrace()
        }
        initData(savedInstanceState)
    }



    override fun onDestroy() {
        super.onDestroy()
        mUnbinder?.run {
            if (this!=null && this != Unbinder.EMPTY) {
                unbind()
                null
            }
        }
        mPresenter?.run {
            if (this!=null) {
                onDestroy()
                null
            }
        }
    }
    override fun useEventBus(): Boolean {
        return true
    }
    /**
     * 这个 [Activity] 是否会使用 [], 框架会根据这个属性判断是否注册 [android.support.v4]
     * 如果返回 `false`, 那意味着这个 [Activity] 不需要绑定 [], 那你再在这个 [Activity] 中绑定继承于 [BaseFragment] 的 [] 将不起任何作用
     * @return 返回 `true` (默认为 `true`), 则需要使用 []
     */
    override fun useFragment(): Boolean {
        return true
    }

    override fun showLoading() {
        showLoadingDialogTypeTwo(this, resources.getString(R.string.im_loading))
    }

    override fun hideLoading() {
        dismissLoadingDialogTypeTwo()
    }

    override fun showMessage(message: String) {
        Preconditions.checkNotNull(message)
        ArmsUtils.snackbarText(message)
    }

    override fun launchActivity(intent: Intent) {
        Preconditions.checkNotNull(intent)
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {
        finish()
    }
}