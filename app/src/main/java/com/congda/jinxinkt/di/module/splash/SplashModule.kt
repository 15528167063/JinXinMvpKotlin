package com.congda.jinxinkt.di.module.splash

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.congda.jinxinkt.mvp.contract.splash.SplashContract
import com.congda.jinxinkt.mvp.model.splash.SplashModel


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
@Module
//构建SplashModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class SplashModule(private val view: SplashContract.View) {
    @ActivityScope
    @Provides
    fun provideSplashView(): SplashContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideSplashModel(model: SplashModel): SplashContract.Model {
        return model
    }
}
