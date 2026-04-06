package com.socialmasla.intel.data.remote

import com.squareup.moshi.Json

data class NewsResponse(
    @Json(name = "articles") val articles: List<ArticleResponse>
)

data class ArticleResponse(
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String,
    @Json(name = "url") val url: String,
    @Json(name = "image") val image: String,
    @Json(name = "publishedAt") val publishedAt: String,
    @Json(name = "source") val source: SourceResponse
)

data class SourceResponse(
    @Json(name = "name") val name: String
)
