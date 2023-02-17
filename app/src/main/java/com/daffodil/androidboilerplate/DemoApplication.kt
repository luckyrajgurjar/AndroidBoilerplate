package com.daffodil.androidboilerplate

import android.app.Application
import android.os.Build
import com.daffodil.androidboilerplate.common.ApiConstants
import com.daffodil.androidboilerplate.network.ServiceManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DemoApplication: Application() {
    override fun onCreate() {
        super.onCreate()
       initApiManager()
    }
    private fun initApiManager() {
        val headersMap = HashMap<String, String>()
        headersMap[ApiConstants.OPERATING_SYSTEM] = "Android"
        headersMap[ApiConstants.OPERATING_SYSTEM_VERSION] = Build.VERSION.RELEASE
        headersMap[ApiConstants.DEVICE_NAME] = Build.BRAND + " " + Build.MODEL
        headersMap["Content-type"] = "application/json"
        ServiceManager().initialize(BuildConfig.APPLICATION_ID, headersMap);
    }
}