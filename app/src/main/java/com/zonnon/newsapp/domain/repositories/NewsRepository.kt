package com.zonnon.newsapp.domain.repositories

import androidx.paging.PagingData
import com.zonnon.newsapp.domain.models.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(sources: List<String>): Flow<PagingData<Article>>
    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>
}
