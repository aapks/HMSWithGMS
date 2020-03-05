package com.halidinvalid.hmswithgms.push

import android.content.Intent
import com.halidinvalid.hmswithgms.BaseActivity
import com.huawei.hms.push.HmsMessageService
import com.huawei.hms.push.RemoteMessage
import com.huawei.hms.push.SendException

class HMSPushService : HmsMessageService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        var message: String? = null

        remoteMessage.apply {
            data?.apply {
                takeIf { this.isNotEmpty() }.apply {
                    message += "Message data : $data"
                }
            }
            notification?.apply {
                message += "message notif. : $body"
            }
        }
        message?.let { sendHmsBroadcast("onMessageReceived", it) }

    }

    override fun onSendError(s: String?, e: Exception?) {
        super.onSendError(s, e)
        val error =
                s + "onSendError called , message id: " + s + " error code : " + (e as SendException).errorCode + " message : " + e.message
        sendHmsBroadcast("onSendError", error)
    }

    private fun sendHmsBroadcast(method: String, message: String) {
        val intent = Intent()
        intent.apply {
            action = BaseActivity.TAG
            putExtra("method", method)
            putExtra("message", message)
            sendBroadcast(this)
        }
    }
}