package com.zonnon.newsapp.domain.usecases.news

import androidx.paging.PagingData
import com.zonnon.newsapp.domain.models.Article
import com.zonnon.newsapp.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }
}
