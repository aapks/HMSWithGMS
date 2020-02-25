package com.halidinvalid.hmswithgms.push

import com.google.firebase.iid.FirebaseInstanceId
import com.halidinvalid.hmswithgms.BaseActivity

class GMSPushNotification(baseActivity: BaseActivity) : BasePushNotification(baseActivity) {

    override fun getToken() {
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener {
            it.apply {
                when {
                    !isSuccessful -> return@addOnCompleteListener
                    else -> {
                        val token = it.result?.token
                        callBack?.apply {
                            onCallBack(token)
                            callBack = null
                        }
                    }
                }
            }
        }
    }
}