package com.socialmasla.intel.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("search")
    suspend fun searchNews(
        @Query("q") query: String = "\"digital marketing\" OR \"online marketing\" OR \"online advertising\" OR \"google ads\" OR \"meta ads\" OR \"Workflow Automation\"",
        @Query("lang") lang: String = "en",
        @Query("apikey") apiKey: String,
        @Query("from") from: String? = null,
        @Query("max") max: Int = 10,
        @Query("in") inField: String = "title"
    ): NewsResponse

    companion object {
        const val BASE_URL = "https://gnews.io/api/v4/"
    }
}
