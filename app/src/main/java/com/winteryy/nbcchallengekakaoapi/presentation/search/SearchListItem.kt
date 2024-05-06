package com.winteryy.nbcchallengekakaoapi.presentation.search

import java.util.Date

sealed interface SearchListItem {
    val title: String
    val thumbnailUrl: String
    val dateTime: Date
    val isBookmarked: Boolean

    data class SearchImageItem(
        override val title: String,
        override val thumbnailUrl: String,
        override val dateTime: Date,
        override val isBookmarked: Boolean
    ): SearchListItem

    data class SearchVideoItem(
        override val title: String,
        override val thumbnailUrl: String,
        override val dateTime: Date,
        override val isBookmarked: Boolean
    ): SearchListItem
}
