package com.congda.jinxinkt.mvp.presenter.fragment

import android.app.Application
import android.content.Context
import com.congda.jinxinkt.mvp.contract.fragment.HomeContract
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 03/27/2020 14:42
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
class HomePresenter
@Inject
constructor(model: HomeContract.Model, rootView: HomeContract.View) :
    BasePresenter<HomeContract.Model, HomeContract.View>(model, rootView) {
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

//    fun ScanPickure(context: Context) {
//        var datas= arrayListOf<ImageInfo>()
//        val images = arrayOf(
//            "http://img6.16fan.com/201510/11/013553aj3kp9u6iuz6k9uj.jpg",
//            "http://img6.16fan.com/201510/11/011753fnanichdca0wbhxc.jpg",
//            "http://img6.16fan.com/201510/11/011819zbzbciir9ctn295o.jpg",
//            "http://img6.16fan.com/201510/11/004847l7w568jc5n5wn385.jpg",
//            "http://img6.16fan.com/201510/11/004906z0a0a0e0hs56ce0t.jpg",
//            "http://img6.16fan.com/201510/11/004937pwttwjt0bgtoton7.jpg",
//            "http://img6.16fan.com/201510/11/004946t38ybzt8bq8c838y.jpg",
//            "http://img6.16fan.com/201510/11/004955d8ftz3t1sttt7ft7.jpg",
//            "http://img6.16fan.com/201510/11/005027qy2g55yyglb59zdu.jpg",
//            "http://img6.16fan.com/201510/11/005229bbtxkczcl0btmw8e.jpg",  // 下面这张是：5760 * 3840
//            "http://img6.16fan.com/attachments/wenzhang/201805/18/152660818127263ge.jpeg",  // 下面这张是：2280 * 22116
//            "http://img6.16fan.com/attachments/wenzhang/201805/18/152660818716180ge.jpeg"
//        )
//        for (i in images.indices) {
//            val bean = ImageInfo()
//            bean.originUrl = images[i]
//            bean.thumbnailUrl = images[i]
//            datas.add(bean)
//        }
//        ImagePreview.getInstance() // 上下文，必须是activity，不需要担心内存泄漏，本框架已经处理好；
//            .setContext(context)
//            .setIndex(2) // 保存的文件夹名称，会在Picture目录进行文件夹的新建。比如："BigImageView"，会在Picture目录新建BigImageView文件夹)
//            .setFolderName("BigImageView") //						.setImageList(datas)
//            .setImageInfoList(datas) // 缩放动画时长，单位ms
//            .setZoomTransitionDuration(3000)
//            .setLoadStrategy(ImagePreview.LoadStrategy.Default) // 是否启用点击图片关闭。默认启用
//            .setEnableClickClose(true) // 是否显示关闭页面按钮，在页面左下角。默认不显示
//            .setShowCloseButton(false) // 设置关闭按钮图片资源，可不填，默认为库中自带：R.drawable.ic_action_close
//            .setCloseIconResId(R.drawable.ic_action_close) // 设置是否显示顶部的指示器（1/9）默认显示
//            .setShowIndicator(true) // 设置顶部指示器背景shape，默认自带灰色圆角shape
//            .setIndicatorShapeResId(R.drawable.shape_indicator_bg) // 设置失败时的占位图，默认为库中自带R.drawable.load_failed，设置为 0 时不显示
//            .setErrorPlaceHolder(R.drawable.load_failed) //设置是否显示下载按钮
//            .setShowDownButton(false) // 点击回调
//            .setBigImageClickListener { activity, view, position -> } // 长按回调
//            .setBigImageLongClickListener { activity, view, position ->
//                true
//            } // 页面切换回调
//            .setBigImagePageChangeListener(object : OnBigImagePageChangeListener {
//                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
//                override fun onPageSelected(position: Int) {}
//                override fun onPageScrollStateChanged(state: Int) {}
//            })
//            .setProgressLayoutId(
//                ImagePreview.PROGRESS_THEME_CIRCLE_TEXT,
//                object : OnOriginProgressListener {
//                    override fun progress(parentView: View, progress: Int) {
//                        Log.d(TAG, "progress: $progress")
//                        // 需要找到进度控件并设置百分比，回调中的parentView即传入的布局的根View，可通过parentView找到控件：
//                        val progressBar = parentView.findViewById<ProgressBar>(R.id.sh_progress_view)
//                        val textView = parentView.findViewById<TextView>(R.id.sh_progress_text)
//                        progressBar.progress = progress
//                        val progressText = "$progress%"
//                        textView.text = progressText
//                    }
//                    override fun finish(parentView: View) {
//                        Log.d(TAG, "finish: ")
//                    }
//                })
//            .start()
//    }
}
