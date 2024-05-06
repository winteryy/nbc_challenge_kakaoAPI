package com.winteryy.nbcchallengekakaoapi.presentation.bookmark

import java.util.Date

data class BookmarkListItem(
    val title: String,
    val thumbnailUrl: String,
    val dateTime: Date,
    val isBookmarked: Boolean
)
