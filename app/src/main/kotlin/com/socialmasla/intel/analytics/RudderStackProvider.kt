package com.socialmasla.intel.analytics

import android.app.Application
import com.rudderstack.sdk.kotlin.android.Analytics
import com.rudderstack.sdk.kotlin.android.Configuration

object RudderStackProvider {
    private var analytics: Analytics? = null

    fun initialize(context: Application, writeKey: String, dataPlaneUrl: String) {
        if (analytics == null) {
            analytics = Analytics(
                configuration = Configuration(
                    writeKey = writeKey,
                    application = context,
                    dataPlaneUrl = dataPlaneUrl
                )
            )
        }
    }

    fun getAnalytics(): Analytics? = analytics
}
