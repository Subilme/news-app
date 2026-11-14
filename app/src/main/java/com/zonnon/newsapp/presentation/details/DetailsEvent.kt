package com.zonnon.newsapp.presentation.details

import com.zonnon.newsapp.domain.models.Article

sealed class DetailsEvent {
    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()
}
