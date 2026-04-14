package com.socialmasla.intel.ui.intel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.socialmasla.intel.data.model.NewsItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.socialmasla.intel.analytics.AnalyticsHelper

import com.socialmasla.intel.BuildConfig
import com.socialmasla.intel.data.repository.NewsRepository
import com.socialmasla.intel.data.remote.NewsApiService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class IntelViewModel : ViewModel() {

    private val repository: NewsRepository by lazy {
        val moshi = com.squareup.moshi.Moshi.Builder()
            .add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory())
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(NewsApiService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        NewsRepository(retrofit.create(NewsApiService::class.java), BuildConfig.GNEWS_API_KEY)
    }

    private val _newsItems = MutableStateFlow<List<NewsItem>>(emptyList())
    val newsItems: StateFlow<List<NewsItem>> = _newsItems.asStateFlow()

    private val _thinkingPhrase = MutableStateFlow("Synthesizing AdTech intelligence...")
    val thinkingPhrase: StateFlow<String> = _thinkingPhrase.asStateFlow()

    private val phrases = listOf(
        "Synthesizing AdTech intelligence...",
        "Calibrating marketing infrastructure...",
        "Optimizing ROAS algorithms...",
        "Authenticating conversion data...",
        "Mapping growth nodes...",
        "Connecting marketing, automation, and conversion..."
    )

    private val _currentIndex = MutableStateFlow(0)
    val currentIndex: StateFlow<Int> = _currentIndex.asStateFlow()

    private val _progress = MutableStateFlow(0f)
    val progress: StateFlow<Float> = _progress.asStateFlow()

    private val _isSwipingForward = MutableStateFlow(true)
    val isSwipingForward: StateFlow<Boolean> = _isSwipingForward.asStateFlow()

    init {
        loadNews()
        startTimer()
        startPhrasing()
    }

    private fun startPhrasing() {
        viewModelScope.launch {
            var index = 0
            while (true) {
                delay(2000)
                index = (index + 1) % phrases.size
                _thinkingPhrase.value = phrases[index]
            }
        }
    }


    private fun loadNews() {
        viewModelScope.launch {
            try {
                val news = repository.getNews()
                if (news.isNotEmpty()) {
                    _newsItems.value = news
                } else {
                    _newsItems.value = getFallbackNews()
                }
            } catch (e: Exception) {
                _newsItems.value = getFallbackNews()
            }
        }
    }

    private fun getFallbackNews(): List<NewsItem> = listOf(
        NewsItem(
            id = "f1",
            title = "AI is Reshaping Performance Marketing",
            summary = "Brands using generative AI for ad creative are seeing 3x higher ROAS — here's why it works.",
            url = "https://socialmasla.com/",
            imageUrl = "",
            publishedAt = "TODAY",
            source = "SOCIAL MASLA"
        ),
        NewsItem(
            id = "f2",
            title = "Why Landing Pages Beat Generic Websites",
            summary = "Editorial landing pages convert 40% better than homepages. The data is clear — intent matters.",
            url = "https://socialmasla.com/case-studies/landing-pages/",
            imageUrl = "",
            publishedAt = "TODAY",
            source = "SOCIAL MASLA"
        ),
        NewsItem(
            id = "f3",
            title = "WhatsApp as a Conversion Engine",
            summary = "Direct-to-consumer brands using WhatsApp API are seeing 60% reply rates. Pulse makes it effortless.",
            url = "https://socialmasla.com/pulse/",
            imageUrl = "",
            publishedAt = "TODAY",
            source = "INTEL BRIEF"
        ),
        NewsItem(
            id = "f4",
            title = "Google's AI Max Campaigns Explained",
            summary = "Everything performance marketers need to know about Google's new AI-first campaign structure.",
            url = "https://socialmasla.com/consultation/",
            imageUrl = "",
            publishedAt = "TODAY",
            source = "INTEL BRIEF"
        ),
        NewsItem(
            id = "f5",
            title = "Meta Advantage+ vs Manual Campaigns",
            summary = "The ongoing debate — when to trust the algorithm and when to take control of targeting yourself.",
            url = "https://socialmasla.com/",
            imageUrl = "",
            publishedAt = "TODAY",
            source = "SOCIAL MASLA"
        )
    )

    private fun startTimer() {
        viewModelScope.launch {
            while (true) {
                delay(100)
                if (_progress.value < 1f) {
                    _progress.value += (1f / 300f) // 30 seconds
                } else {
                    swipeNext()
                }
            }
        }
    }

    fun swipeNext() {
        if (_newsItems.value.isEmpty()) return
        
        val currentTitle = _newsItems.value.getOrNull(_currentIndex.value)?.title
        currentTitle?.let { AnalyticsHelper.logIntelSwipe(it) }

        _isSwipingForward.value = true
        _progress.value = 0f
        if (_currentIndex.value < _newsItems.value.size - 1) {
            _currentIndex.value += 1
        } else {
            _currentIndex.value = 0
        }
    }

    fun swipePrevious() {
        if (_newsItems.value.isEmpty()) return
        
        _isSwipingForward.value = false
        _progress.value = 0f
        if (_currentIndex.value > 0) {
            _currentIndex.value -= 1
        } else {
            _currentIndex.value = _newsItems.value.size - 1
        }
    }
}
