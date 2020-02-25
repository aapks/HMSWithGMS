package com.halidinvalid.hmswithgms

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.halidinvalid.hmswithgms.push.GMSPushNotification
import com.halidinvalid.hmswithgms.push.OnPushCallBack

class BaseActivity : AppCompatActivity() {

    private lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val basePushNotification =
                GMSPushNotification(this)
        basePushNotification.setOnPushCallBack(object :
                OnPushCallBack {
            override fun onCallBack(result: String?) {
                token = result.toString()
            }
        })
        basePushNotification.getToken()
    }

    companion object {
        const val TAG = "BaseActivity"
    }
}