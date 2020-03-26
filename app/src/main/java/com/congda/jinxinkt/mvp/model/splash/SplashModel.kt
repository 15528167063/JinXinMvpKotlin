package com.congda.jinxinkt.mvp.model.splash

import android.app.Application
import com.congda.jinxinkt.mvp.contract.splash.SplashContract
import com.congda.jinxinkt.mvp.model.api.Api
import com.congda.jinxinkt.mvp.model.entity.CommonBean
import com.congda.jinxinkt.mvp.model.entity.IMHttpResult
import com.congda.jinxinkt.mvp.model.entity.SplashAdBean
import com.congda.jinxinkt.mvp.model.entity.VersonBeanData
import com.google.gson.Gson
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.RequestBody
import javax.inject.Inject


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 03/26/2020 16:09
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class SplashModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    SplashContract.Model {
    @Inject
    lateinit var mGson: Gson;
    @Inject
    lateinit var mApplication: Application;

    override fun getGetAdJson(): Observable<IMHttpResult<List<SplashAdBean>>>{
        return mRepositoryManager.obtainRetrofitService(Api::class.java).httpGetAdJson()
    }

    override fun CheckedVersion(systemType: String):Observable<IMHttpResult<VersonBeanData>> {
        val commonBean = CommonBean()
        commonBean.systemType=systemType;
        val toJson = Gson().toJson(commonBean)
        val body: RequestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), toJson)
        return mRepositoryManager.obtainRetrofitService(Api::class.java).getVersonBean(body)
    }

    override fun onDestroy() {
        super.onDestroy();
    }

}
