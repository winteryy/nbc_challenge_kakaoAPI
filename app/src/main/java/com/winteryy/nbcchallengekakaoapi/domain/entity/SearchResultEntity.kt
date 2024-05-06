package com.winteryy.nbcchallengekakaoapi.domain.entity

import java.util.Date

data class SearchResultEntity<T>(
    val meta: MetaEntity,
    val documents: List<T>
)

data class IntegratedSearchResultEntity(
    val imageMeta: MetaEntity,
    val videoMeta: MetaEntity,
    val documents: List<SearchItemEntity>
)

data class MetaEntity(
    val totalCount: Int,
    val pageableCount: Int,
    val isEnd: Boolean
)

sealed interface SearchItemEntity {
    val dateTime: Date
    val isBookmarked: Boolean

    data class ImageItemEntity(
        val collection: String,
        val thumbnailUrl: String,
        val imageUrl: String,
        val width: Int,
        val height: Int,
        val siteName: String,
        val docUrl: String,
        override val dateTime: Date,
        override val isBookmarked: Boolean,
    ): SearchItemEntity

    data class VideoItemEntity(
        val title: String,
        val url: String,
        val playTime: Int,
        val thumbnail: String,
        val author: String,
        override val dateTime: Date,
        override val isBookmarked: Boolean
    ): SearchItemEntity
}



