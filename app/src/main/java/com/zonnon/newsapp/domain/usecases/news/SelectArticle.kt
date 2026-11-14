package com.zonnon.newsapp.domain.usecases.news

import com.zonnon.newsapp.domain.models.Article
import com.zonnon.newsapp.domain.repositories.NewsRepository

class SelectArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String): Article? {
        return newsRepository.getArticle(url = url)
    }
}
