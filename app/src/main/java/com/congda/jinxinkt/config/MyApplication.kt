package com.congda.jinxinkt.config

import android.content.Context
import com.congda.baselibrary.BaseModuManager
import com.congda.baselibrary.imutils.IMCrashHandler
import com.jess.arms.base.BaseApplication

class MyApplication : BaseApplication() {
    companion object {
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        BaseModuManager.init(this)
        val crashHandler = IMCrashHandler.getInstance()
        crashHandler.init(applicationContext)
    }


}