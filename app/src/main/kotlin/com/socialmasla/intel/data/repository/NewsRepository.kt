package com.socialmasla.intel.data.repository

import com.socialmasla.intel.data.model.NewsItem
import com.socialmasla.intel.data.remote.NewsApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class NewsRepository(
    private val apiService: NewsApiService,
    private val apiKey: String
) {
    private var cachedNews: List<NewsItem> = emptyList()
    private var lastFetchTime: Long = 0
    private val CACHE_TIMEOUT = 30 * 60 * 1000 // 30 minutes in milliseconds

    private fun getISODate7DaysAgo(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -7)
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        format.timeZone = TimeZone.getTimeZone("UTC")
        return format.format(calendar.time)
    }

    suspend fun getNews(): List<NewsItem> {
        val currentTime = System.currentTimeMillis()
        if (cachedNews.isNotEmpty() && (currentTime - lastFetchTime) < CACHE_TIMEOUT) {
            return cachedNews
        }

        return try {
            val fromDate = getISODate7DaysAgo()
            val response = apiService.searchNews(apiKey = apiKey, from = fromDate)
            val items = response.articles.mapIndexed { index, article ->
                NewsItem(
                    id = index.toString(),
                    title = article.title,
                    summary = article.description ?: "",
                    url = article.url,
                    imageUrl = article.image ?: "",
                    publishedAt = article.publishedAt,
                    source = article.source.name
                )
            }
            if (items.isNotEmpty()) {
                cachedNews = items
                lastFetchTime = currentTime
            }
            items
        } catch (e: Exception) {
            cachedNews.ifEmpty { emptyList() }
        }
    }
}
