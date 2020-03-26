package com.congda.jinxinkt.mvp.model.entity

class IMHttpResult<T> (
    var code: String,
    var status: String,
    var message: String,
    var data: T
)
