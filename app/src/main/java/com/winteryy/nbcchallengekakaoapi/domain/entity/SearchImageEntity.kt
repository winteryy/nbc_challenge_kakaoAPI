package com.winteryy.nbcchallengekakaoapi.domain.entity

import java.util.Date

data class SearchImageEntity(
    val meta: Meta,
    val searchImageList: List<ImageItem>
)

data class Meta(
    val totalCount: Int,
    val pageableCount: Int,
    val isEnd: Boolean
)

data class ImageItem(
    val collection: String,
    val thumbnailUrl: String,
    val imageUrl: String,
    val width: Int,
    val height: Int,
    val siteName: String,
    val docUrl: String,
    val datetime: Date,
    val isBookmarked: Boolean
)
