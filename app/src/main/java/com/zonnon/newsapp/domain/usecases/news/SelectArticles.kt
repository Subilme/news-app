package com.zonnon.newsapp.domain.usecases.news

import com.zonnon.newsapp.data.local.NewsDao
import com.zonnon.newsapp.domain.models.Article
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsDao: NewsDao
) {
    operator fun invoke() : Flow<List<Article>> {
        return newsDao.getArticles()
    }
}
