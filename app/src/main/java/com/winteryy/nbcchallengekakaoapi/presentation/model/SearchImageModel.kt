package com.winteryy.nbcchallengekakaoapi.presentation.model

import java.util.Date

data class SearchImageResult(
    val meta: ResultMeta,
    val searchImageList: List<ImageListItem>
)

data class ResultMeta(
    val totalCount: Int,
    val pageableCount: Int,
    val isEnd: Boolean
)

data class ImageListItem(
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
