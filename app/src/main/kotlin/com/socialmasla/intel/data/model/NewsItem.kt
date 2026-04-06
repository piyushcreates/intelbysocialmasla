package com.socialmasla.intel.data.model

data class NewsItem(
    val id: String,
    val title: String,
    val summary: String,
    val url: String,
    val imageUrl: String,
    val publishedAt: String,
    val source: String
)
