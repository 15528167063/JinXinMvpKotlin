package com.congda.baselibrary.base

import android.Manifest
import android.os.Bundle
import com.congda.baselibrary.R
import com.congda.baselibrary.imutils.IMLogUtil
import com.congda.baselibrary.imutils.IMStatusBarUtil
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks

/**
 * @author jinxin
 * 剑之所指，心之所向
 * @date 2019/8/4
 */
abstract class BaseActivity : JxSwipeBackActivity(), PermissionCallbacks {
    var perms = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initStatusBar()
        requestPermissions()
    }

    /**
     * 默认状态栏白底黑字
     */
   open fun initStatusBar() {
        IMStatusBarUtil.setColor(this, resources.getColor(R.color.white), 0)
        IMStatusBarUtil.setLightMode(this)
    }

    /**
     * 动态添加权限
     */
    open fun requestPermissions() {
        if (EasyPermissions.hasPermissions(this, *perms)) { //todo 已经获取了权限
        } else { //没有获取了权限，申请权限
            EasyPermissions.requestPermissions(this, "为了优化您的使用体验，请打开下列必要的权限", 0, *perms)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
    }
}