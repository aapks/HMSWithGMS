package com.halidinvalid.hmswithgms.push

import android.util.Log
import com.halidinvalid.hmswithgms.BaseActivity
import com.huawei.agconnect.config.AGConnectServicesConfig
import com.huawei.hms.aaid.HmsInstanceId
import kotlinx.coroutines.*


class HMSPushNotification(baseActivity: BaseActivity) : BasePushNotification(baseActivity) {
    var appId: String? = null

    override fun getToken() {

        if (appId == null)
            appId = AGConnectServicesConfig.fromContext(baseActivity).getString("client/app_id")

        CoroutineScope(Dispatchers.Main + Job()).launch {
            doToken()
        }
    }

    private suspend fun doToken() = withContext(Dispatchers.IO) {
        try {
            val token = HmsInstanceId.getInstance(baseActivity).getToken(appId, "HCM")
            token?.let {
                Log.i("token", "get token :$it")
                callBack?.apply {
                    onCallBack(it)
                    callBack = null
                }
            }
        } catch (e: Exception) { //Acquisition failure, print logs
            Log.e("token", "hms token error", e)
        }
    }

}