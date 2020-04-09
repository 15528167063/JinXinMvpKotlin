package com.congda.jinxinkt.mvp.presenter.fragment

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.DialogInterface
import android.view.View
import androidx.fragment.app.FragmentActivity
import cc.shinichi.library.ImagePreview
import cc.shinichi.library.bean.ImageInfo
import cc.shinichi.library.view.listener.OnBigImagePageChangeListener
import com.congda.baselibrary.widget.IMSavePictureUtils
import com.congda.baselibrary.widget.IMSheetDialog
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
    BasePresenter<HomeContract.Model, HomeContract.View>(model, rootView),
    OnBigImagePageChangeListener {
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

    fun showBigImageView(activity: FragmentActivity?) {
        val imageInfoList: MutableList<ImageInfo> = mutableListOf();
        var imageInfo = ImageInfo()
        imageInfo.setOriginUrl("http://47.244.137.17:9050/group1/M00/00/11/rB8eHF6Byd2AWUmzAAECP63pVyA18.jpeg")
        imageInfo.setThumbnailUrl("http://47.244.137.17:9050/group1/M00/00/11/rB8eHF6Byd2ATSIHAAAVM3MJdYM67.jpeg")
        imageInfoList.add(0, imageInfo)

        imageInfo = ImageInfo()
        imageInfo.setOriginUrl("http://47.244.137.17:9050/group1/M00/00/11/rB8eHF6Byd2ALyMTAAD328O0VTE12.jpeg")
        imageInfo.setThumbnailUrl("http://47.244.137.17:9050/group1/M00/00/11/rB8eHF6Byd2AFbFgAAAPzOcoRi469.jpeg")
        imageInfoList.add(0, imageInfo)

        imageInfo = ImageInfo()
        imageInfo.setOriginUrl("http://47.244.137.17:9050/group1/M00/00/11/rB8eHF6Byd2ARDhPAADDpOED4bk42.jpeg")
        imageInfo.setThumbnailUrl("http://47.244.137.17:9050/group1/M00/00/11/rB8eHF6Byd2ARDACAAARD8fqdEI88.jpeg")
        imageInfoList.add(0, imageInfo)

        imageInfo = ImageInfo()
        imageInfo.setOriginUrl("http://47.244.137.17:9050/group1/M00/00/11/rB8eHF6Byd2AJ1yQAADNV_mcurE76.jpeg")
        imageInfo.setThumbnailUrl("http://47.244.137.17:9050/group1/M00/00/11/rB8eHF6Byd2ASeHlAAAPFoY5eMs66.jpeg")
        imageInfoList.add(0, imageInfo)

        imageInfo = ImageInfo()
        imageInfo.setOriginUrl("http://47.244.137.17:9050/group1/M00/00/11/rB8eHF6Byd2AZgfmAAFnc0C7xH872.jpeg")
        imageInfo.setThumbnailUrl("http://47.244.137.17:9050/group1/M00/00/11/rB8eHF6Byd2AHwWyAAAUdyLUDpg70.jpeg")
        imageInfoList.add(0, imageInfo)

        ImagePreview.getInstance() // 上下文，必须是activity，不需要担心内存泄漏，本框架已经处理好；
            .setContext(activity!!)
            .setLoadStrategy(ImagePreview.LoadStrategy.Default)
            .setZoomTransitionDuration(1000)
            .setImageInfoList(imageInfoList) //设置是否显示下载按钮
            //设置是否显示下载按钮
            .setShowDownButton(false)
            // 点击回调
            .setBigImageClickListener{ activity: Activity, view: View, i: Int ->{}
            }
            .setBigImageLongClickListener{ activity: Activity, view: View, i: Int ->
                IMSheetDialog.Builder(activity)
                    .addSheet("保存图片") { dialog : DialogInterface, which :Int ->
                        dialog.dismiss()
                        ImagePreview.getInstance().setFolderName("JinXin")
                        IMSavePictureUtils.downloadPicture(activity, imageInfoList.get(i).getOriginUrl())
                    }
                    .addSheet("分享好友") { dialog : DialogInterface, which :Int ->
                        dialog.dismiss()
                    }.create().show();
                return@setBigImageLongClickListener true}
            .setBigImagePageChangeListener(this)
            .start()
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
    }

}
