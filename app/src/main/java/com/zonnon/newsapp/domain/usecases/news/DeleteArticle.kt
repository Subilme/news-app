package com.zonnon.newsapp.domain.usecases.news

import com.zonnon.newsapp.domain.models.Article
import com.zonnon.newsapp.domain.repositories.NewsRepository

class DeleteArticle(
private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article: Article) {
        newsRepository.deleteArticle(article = article)
    }
}
