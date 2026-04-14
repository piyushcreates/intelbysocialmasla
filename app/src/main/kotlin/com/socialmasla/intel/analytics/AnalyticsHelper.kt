package com.socialmasla.intel.analytics

import android.content.Context
import android.os.Bundle
import com.facebook.appevents.AppEventsConstants
import com.facebook.appevents.AppEventsLogger
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.FirebaseAnalytics.Event
import com.google.firebase.analytics.FirebaseAnalytics.Param

object AnalyticsHelper {
    private var firebaseAnalytics: FirebaseAnalytics? = null
    private var fbLogger: AppEventsLogger? = null

    fun initialize(context: Context) {
        if (firebaseAnalytics == null) {
            firebaseAnalytics = FirebaseAnalytics.getInstance(context)
            fbLogger = AppEventsLogger.newLogger(context)
        }
    }

    fun logIntelSwipe(title: String) {
        val bundle = Bundle().apply {
            putString("item_title", title)
        }
        firebaseAnalytics?.logEvent("intel_card_swipe", bundle)
        fbLogger?.logEvent("intel_card_swipe", bundle)
    }

    // STANDARDIZED: "ViewContent" for News
    fun logReadMore(url: String) {
        val bundle = Bundle().apply {
            putString(Param.ITEM_CATEGORY, "news_article")
            putString(Param.ITEM_ID, url)
            putString(Param.CONTENT_TYPE, "intel_news")
        }
        // Google: view_item
        firebaseAnalytics?.logEvent(Event.VIEW_ITEM, bundle)
        
        // Meta: ViewContent with category
        val metaBundle = Bundle(bundle).apply {
            putString("fb_content_category", "news_article")
        }
        fbLogger?.logEvent(AppEventsConstants.EVENT_NAME_VIEWED_CONTENT, metaBundle)
    }

    // STANDARDIZED: "ViewContent" for Service Interests
    fun logServiceClick(serviceName: String) {
        val bundle = Bundle().apply {
            putString(Param.ITEM_NAME, serviceName)
            putString(Param.ITEM_CATEGORY, "service_detail")
            putString(Param.CONTENT_TYPE, "social_masla_service")
        }
        // Google: view_item (unified with news for better AI training)
        firebaseAnalytics?.logEvent(Event.VIEW_ITEM, bundle)
        
        // Meta: ViewContent with distinct category
        val metaBundle = Bundle(bundle).apply {
            putString("fb_content_category", "service_detail")
        }
        fbLogger?.logEvent(AppEventsConstants.EVENT_NAME_VIEWED_CONTENT, metaBundle)
    }
}
