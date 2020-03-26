package com.congda.jinxinkt.mvp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.congda.baselibrary.loading.ShowLoadiongUtils
import com.congda.baselibrary.loading.ShowLoadiongUtils.dismissLoadingDialogTypeOne
import com.congda.baselibrary.loading.ShowLoadiongUtils.showLoadingDialogTypeOne
import com.congda.jinxinkt.R
import com.jess.arms.base.delegate.IFragment
import com.jess.arms.integration.cache.Cache
import com.jess.arms.integration.cache.CacheType
import com.jess.arms.integration.lifecycle.FragmentLifecycleable
import com.jess.arms.mvp.IPresenter
import com.jess.arms.mvp.IView
import com.jess.arms.utils.ArmsUtils
import com.jess.arms.utils.Preconditions
import com.trello.rxlifecycle2.android.FragmentEvent
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import javax.inject.Inject
/**
 * 因为 Java 只能单继承, 所以如果要用到需要继承特定 [Activity] 的三方库, 那你就需要自己自定义 [Activity]
 * 继承于这个特定的 [Activity], 然后再按照 [com.jess.arms.base.BaseActivity] 的格式, 将代码复制过去, 记住一定要实现[IActivity]
 */
abstract class IMBaseFragment<P : IPresenter?> : Fragment(), IFragment, FragmentLifecycleable, IView {
    protected val TAG = this.javaClass.simpleName
    private val mLifecycleSubject = BehaviorSubject.create<FragmentEvent>()
    private var mCache: Cache<Any, Any>? = null
    protected var mContext: Context? = null
    @JvmField
    @Inject
    var mPresenter: P? = null//如果当前页面逻辑简单, Presenter 可以为 null

    @Synchronized
    override fun provideCache(): Cache<String, Any> {
        if (mCache == null) {
            mCache = ArmsUtils.obtainAppComponentFromContext(activity).cacheFactory().build(CacheType.FRAGMENT_CACHE)
        }
        return mCache as Cache<String, Any>
    }

    override fun provideLifecycleSubject(): Subject<FragmentEvent> {
        return mLifecycleSubject
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return initView(inflater, container, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.run {
            if (this!=null) {
                onDestroy()
                null
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        mContext = null
    }
    override fun useEventBus(): Boolean {
        return true
    }

    override fun showLoading() {
        ShowLoadiongUtils.showLoadingDialogTypeTwo(activity!!, resources.getString(R.string.im_loading))
    }

    override fun hideLoading() {
        ShowLoadiongUtils.dismissLoadingDialogTypeTwo()
    }

    override fun showMessage(message: String) {
        Preconditions.checkNotNull(message)
        ArmsUtils.snackbarText(message)
    }

    override fun launchActivity(intent: Intent) {
        Preconditions.checkNotNull(intent)
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {}
}