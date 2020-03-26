package com.congda.jinxinkt.mvp.model.api

import com.congda.jinxinkt.mvp.model.entity.IMHttpResult
import com.congda.jinxinkt.mvp.model.entity.IMUpdataFileBean
import com.congda.jinxinkt.mvp.model.entity.SplashAdBean
import com.congda.jinxinkt.mvp.model.entity.VersonBeanData
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface Api {
    /**
     * （通过get请求）https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b
     */
    @GET("in_theaters")
    fun login( @Query("apikey") apiKey: String): Observable<Any>

    /**
     *上传图片
     */
    @Multipart
    @POST("api/v1/message/uploadFile")
    fun getUpdataPictureFile(@Part partList: List<MultipartBody.Part>): Observable<IMUpdataFileBean>

    /**
     * （通过post json请求）
     */
    @POST("tomato-app/commons/check-update")
    fun getVersonBean(@Body body: RequestBody): Observable<IMHttpResult<VersonBeanData>>

    /**
     * 获取广告
     */
    @POST("tomato-app/front-ads/get")
    fun httpGetAdJson(): Observable<IMHttpResult<List<SplashAdBean>>>

}