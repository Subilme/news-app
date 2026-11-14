package com.zonnon.newsapp.domain.usecases.news

import com.zonnon.newsapp.domain.models.Article
import com.zonnon.newsapp.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsRepository: NewsRepository
) {
    operator fun invoke() : Flow<List<Article>> {
        return newsRepository.getArticles()
    }
}
