package com.zonnon.newsapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Article(
    val source: Source,
    val author: String? = null,
    val title: String,
    val description: String,
    @PrimaryKey val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)
