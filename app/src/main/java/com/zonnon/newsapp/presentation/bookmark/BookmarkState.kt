package com.zonnon.newsapp.presentation.bookmark

import com.zonnon.newsapp.domain.models.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)
