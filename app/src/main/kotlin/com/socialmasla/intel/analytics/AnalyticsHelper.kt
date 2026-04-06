package com.socialmasla.intel.analytics

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject

class AnalyticsHelper(
    private val firebaseAnalytics: FirebaseAnalytics
) {
    fun logIntelSwipe(title: String) {
        val bundle = Bundle().apply {
            putString("item_title", title)
        }
        firebaseAnalytics.logEvent("intel_card_swipe", bundle)
    }

    fun logServiceClick(serviceName: String) {
        val bundle = Bundle().apply {
            putString("service_name", serviceName)
        }
        firebaseAnalytics.logEvent("services_cta_click", bundle)
    }

    fun logReadMore(url: String) {
        val bundle = Bundle().apply {
            putString("url", url)
        }
        firebaseAnalytics.logEvent("intel_read_more", bundle)
    }
}
