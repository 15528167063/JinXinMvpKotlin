package com.congda.jinxinkt.mvp.ui.fragment

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.congda.baselibrary.imutils.IMChooseUtils
import com.congda.baselibrary.widget.IMSheetDialog
import com.congda.baselibrary.widget.IMSheetViewDialog
import com.congda.baselibrary.widget.glide.IMImageLoadUtil
import com.congda.jinxinkt.R
import com.congda.jinxinkt.di.component.fragment.DaggerHomeComponent
import com.congda.jinxinkt.di.module.fragment.HomeModule
import com.congda.jinxinkt.mvp.contract.fragment.HomeContract
import com.congda.jinxinkt.mvp.presenter.fragment.HomePresenter
import com.congda.jinxinkt.mvp.ui.IMBaseFragment
import com.donkingliang.imageselector.utils.ImageSelector
import com.jess.arms.di.component.AppComponent
import com.zhihu.matisse.Matisse
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : IMBaseFragment<HomePresenter>(), HomeContract.View, View.OnClickListener ,IMSheetViewDialog.Callback{
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
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)
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
            R.id.btn4->{

            }
            R.id.iv1->{
                showBigImage()
            }

        }
    }


    private fun showSheetView() {
        IMSheetDialog.Builder(activity)
            .addSheet("拍照", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
                ImageSelector.builder()
                    .setCrop(true) // 设置是否使用图片剪切功能。
                    .setCropRatio(1.0f) // 图片剪切的宽高比,默认1.0f。宽固定为手机屏幕的宽。
                    .onlyTakePhoto(true)  // 仅拍照，不打开相册
                    .start(activity, 10002);
            })
            .addSheet("选择图片", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
                IMChooseUtils.choosePhotoForResult(activity,10001,9)
            })
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
        mPresenter?.showBigImageView(activity)
    }

    //选择图片
    public fun setPickUre(data: Intent?,type : Int=0) {
        if(type==10001){
            val paths = Matisse.obtainPathResult(data)
            activity?.let { IMImageLoadUtil.CommonImageLineCircleLoad(it,paths[0],iv1) }
        }else{
            val isCameraImage = data?.getBooleanExtra(ImageSelector.IS_CAMERA_IMAGE, false)
            val images = data?.getStringArrayListExtra(ImageSelector.SELECT_RESULT)
            activity?.let { IMImageLoadUtil.CommonImageLineCircleLoad(it, images?.get(0),iv1) }
        }
    }


}
