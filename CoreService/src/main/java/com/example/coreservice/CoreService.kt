package com.example.coreservice

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log

class CoreService {
    companion object {
        private var appId: String? = null
        private const val TAG = "AppsOnAirCoreServices"

        @JvmStatic
        fun getAppId(context: Context): String {
            return try {
                val appInfo = context.packageManager.getApplicationInfo(
                    context.packageName, PackageManager.GET_META_DATA)

                val bundle = appInfo.metaData
                if (bundle != null) {
                    // Retrieve App id from AndroidManifest file
                    appId = bundle.getString("app_id")
                    Log.d(TAG, appId ?: "App ID is null")
                    appId ?: ""
                } else {
                    // Handle case where metadata bundle is null
                    Log.d(TAG, "Set app id in android manifest file")
                    ""
                }
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
                // Handle exception
                Log.d(TAG, "Catch")
                ""
            }
        }
    }
}