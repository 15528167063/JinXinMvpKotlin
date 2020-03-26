package com.congda.baselibrary

import android.content.Context
import com.congda.baselibrary.imutils.IMCrashHandler
import com.congda.baselibrary.imutils.IMPreferenceUtil

/**
 * @author jinxin
 * 剑之所指，心之所向
 * @date 2019/8/11
 */
class BaseModuManager(val context: Context) {

    companion object {
        private var instance: BaseModuManager? = null
        @JvmStatic
        fun getInstance(): BaseModuManager? {
            if (instance == null) {
                throw RuntimeException("必须先调用静态方法init(Context context)")
            }
            return instance
        }
        fun init(context: Context?) {
            if (context == null) {
                throw NullPointerException("context不能为空")
            }
            if (instance == null)
                synchronized(BaseModuManager::class.java) {
                    if (instance == null)
                        instance = BaseModuManager(context.applicationContext)
                }
        }
    }
    /**
     * 初始化module里面的部分属性，并且开启认证身份和开启接受消息的服务
     */
    init {
        IMPreferenceUtil.init(context)
        val crashHandler = IMCrashHandler.getInstance()
        crashHandler.init(context)
    }
}