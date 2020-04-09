package com.congda.jinxinkt.mvp.ui.fragment

import android.app.Activity
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cc.shinichi.library.ImagePreview
import cc.shinichi.library.bean.ImageInfo
import cc.shinichi.library.view.listener.OnBigImageClickListener
import cc.shinichi.library.view.listener.OnBigImageLongClickListener
import cc.shinichi.library.view.listener.OnBigImagePageChangeListener
import com.congda.baselibrary.widget.IMSavePictureUtils
import com.congda.baselibrary.widget.IMSheetDialog
import com.congda.baselibrary.widget.IMSheetViewDialog
import com.congda.baselibrary.widget.glide.IMImageLoadUtil
import com.congda.jinxinkt.R
import com.congda.jinxinkt.di.component.fragment.DaggerHomeComponent
import com.congda.jinxinkt.di.module.fragment.HomeModule
import com.congda.jinxinkt.mvp.contract.fragment.HomeContract
import com.congda.jinxinkt.mvp.presenter.fragment.HomePresenter
import com.congda.jinxinkt.mvp.ui.IMBaseFragment
import com.jess.arms.di.component.AppComponent
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : IMBaseFragment<HomePresenter>(), HomeContract.View, View.OnClickListener ,IMSheetViewDialog.Callback,
    OnBigImagePageChangeListener {
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
        iv1.setOnClickListener (this)
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        activity?.let { IMImageLoadUtil.CommonImageLineCircleLoad(it,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1585564389613&di=59413e2550ba181876cd2aab46d0ecab&imgtype=0&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Fd62a6059252dd42a1c362a29033b5bb5c9eab870.jpg",iv1) }



    }

    override fun setData(data: Any?) {

    }





    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btn1->{
                IMSheetViewDialog().show(activity?.supportFragmentManager,this)
            }
            R.id.btn2->{
                activity?.let { showSheetView() }
            }
            R.id.btn3->{
                showLoading()
                btn2.postDelayed({hideLoading()},2000)
            }
            R.id.iv1->{
                showBigImage();
            }
        }
    }



    private fun showSheetView() {
        IMSheetDialog.Builder(activity)
            .addSheet("拍照", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
            .addSheet("选择图片", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
            .create().show()
    }

    override fun onClick(position: Int) {
        when(position){
            0->{
                showMessage("0")
            }
            1->{
                showMessage("1")
            }
            2->{
                showMessage("2")
            }
        }
    }

    //查看大图
    private fun showBigImage() {
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
            .setContext(context!!)
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
                    .addSheet("保存图片") { dialog :DialogInterface, which :Int ->
                        dialog.dismiss()
                        ImagePreview.getInstance().setFolderName("JinXin")
                        IMSavePictureUtils.downloadPicture(activity, imageInfoList.get(i).getOriginUrl())
                    }
                    .addSheet("分享好友") { dialog :DialogInterface, which :Int ->
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
