package com.halidinvalid.hmswithgms.push

import com.halidinvalid.hmswithgms.BaseActivity

abstract class BasePushNotification(
        var baseActivity: BaseActivity,
        var callBack: OnPushCallBack? = null
) {
    abstract fun getToken()

    fun setOnPushCallBack(onPushCallBack: OnPushCallBack) {
        this.callBack = onPushCallBack
    }
}