package com.congda.jinxinkt.mvp.model.entity

/**
 * Created by csx on 2017/10/23.
 */
data  class IMUpdataFileBean (
    var status: String,
    var message: String,
    var data: IMUpdataFile,
    var objectkey: String,
    var ownurl: String
){
    data class IMUpdataFile (
        var fileName: String,
        var filePath: String,
        var thumbnailpath: String,
        var fileType: String,
        var objectkey: String
    )
}

