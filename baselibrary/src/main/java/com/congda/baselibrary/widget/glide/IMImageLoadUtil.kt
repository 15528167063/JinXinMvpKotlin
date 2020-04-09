package com.congda.baselibrary.widget.glide

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.congda.baselibrary.R
import com.congda.baselibrary.imutils.IMDensityUtil
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 * @author jinxin
 * 剑之所指，心之所向
 * @date 2019/8/4
 * 加载图片类
 */
object IMImageLoadUtil {
    /**
     * 加载普通网络图片
     */
    fun CommonImageLoad(context: Context?, url: String?, imageView: ImageView?) {
        Glide.with(context!!)
            .load(url)
            .placeholder(R.mipmap.im_icon_stub_loading)
            .error(R.mipmap.im_icon_stub)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .into(imageView!!)
    }

    /**
     * 加载普通类型的图片centerCrop
     */
    fun CommonImageLoadCp(context: Context?, url: String?, imageView: ImageView?) {
        Glide.with(context!!)
            .load(url)
            .placeholder(R.mipmap.im_icon_stub_loading)
            .error(R.mipmap.im_icon_stub)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(imageView!!)
    }

    /**
     * 加载圆形图片
     */
    fun CommonImageCircleLoad(context: Context?, url: String?, imageView: ImageView?) {
        val requestOptions = RequestOptions.circleCropTransform()
        Glide.with(context!!)
            .load(url)
            .placeholder(R.mipmap.im_icon_stub_loading_circle)
            .error(R.mipmap.im_icon_stub_circle)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .apply(requestOptions)
            .into(imageView!!)
    }

    /**
     * 加载圆形图片(带边框)
     */
    fun CommonImageLineCircleLoad(context: Context, url: String?, imageView: ImageView?) {
        val options = RequestOptions()
            .placeholder(R.mipmap.im_icon_stub_loading_circle)
            .error(R.mipmap.im_icon_stub_circle)
            .optionalTransform(CircleCrop())
            .transform(IMGlideCircleWithBorder(context, 2, context.resources.getColor(R.color.color_0BD2CF)))
        Glide.with(context)
            .load(url)
            .apply(options)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .into(imageView!!)
    }

    /**
     * 圆角图片
     */
    fun CommonImageRoundLoad(context: Context, url: String?, imageView: ImageView?) {
        val transform = RoundedCornersTransformation(IMDensityUtil.dip2px(context, 5f), 0)
        val options = RequestOptions().placeholder(R.mipmap.im_icon_stub).error(R.mipmap.im_icon_stub).transform(transform)
        Glide.with(context!!)
            .asBitmap()
            .load(url)
            .dontAnimate()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .apply(options)
            .dontAnimate()
            .into(imageView!!)
    }

    /**
     * 加载普通类型的图片fitCenter(自定义loading)
     */
    fun CommonImageLoadFc(context: Context?, url: String?, imageView: ImageView?) {
        val anim = ObjectAnimator.ofInt(imageView, "ImageLevel", 0, 10000)
        anim.duration = 800
        anim.repeatCount = ObjectAnimator.INFINITE
        anim.start()
        Glide.with(context!!)
            .load(url)
            .placeholder(R.drawable.im_image_loading)
            .error(R.mipmap.im_icon_stub)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable?>, isFirstResource: Boolean): Boolean {
                    anim.cancel()
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any, target: Target<Drawable?>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                    anim.cancel()
                    return false
                }
            })
            .fitCenter()
            .into(imageView!!)
    }

    /**
     * 加载渐渐显示的效果(只在启动页使用)
     */
    fun CommonSplashImageLoadCp(context: Context?, url: String?, imageView: ImageView?) {
        val drawableCrossFadeFactory = DrawableCrossFadeFactory.Builder(300).setCrossFadeEnabled(true).build()
        Glide.with(context!!)
            .load(url)
            .placeholder(R.mipmap.im_splash_bg)
            .error(R.mipmap.im_splash_bg)
            .transition(DrawableTransitionOptions.with(drawableCrossFadeFactory))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(imageView!!)
    }
    /**
     * 加载gif
     */
    fun CommonGifLoadCp(context: Context?, url: String?, imageView: ImageView?) {
        Glide.with(context!!).load(url).listener(object : RequestListener<Drawable?> {
            override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable?>, isFirstResource: Boolean): Boolean {
                return false
            }
            override  fun onResourceReady(resource: Drawable?, model: Any, target: Target<Drawable?>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                if (resource is GifDrawable) { //加载一次
                    resource.setLoopCount(-1)
                }
                return false
            }

        }).into(imageView!!)
    }
}

