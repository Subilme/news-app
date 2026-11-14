package com.zonnon.newsapp.data.remote.dto

import com.zonnon.newsapp.domain.models.Article

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)
