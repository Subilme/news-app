package com.zonnon.newsapp.domain.usecases.news

import com.zonnon.newsapp.data.local.NewsDao
import com.zonnon.newsapp.domain.models.Article

class UpsertArticle(
    private val newsDao: NewsDao
) {
    suspend operator fun invoke(article: Article) {
        newsDao.upsert(article)
    }
}
