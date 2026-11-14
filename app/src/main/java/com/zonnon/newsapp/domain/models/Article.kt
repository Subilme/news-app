package com.zonnon.newsapp.domain.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
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
) : Parcelable
