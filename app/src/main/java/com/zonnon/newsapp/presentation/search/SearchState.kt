package com.zonnon.newsapp.presentation.search

import androidx.paging.PagingData
import com.zonnon.newsapp.domain.models.Article
import kotlinx.coroutines.flow.Flow

data class SearchState (
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
)
