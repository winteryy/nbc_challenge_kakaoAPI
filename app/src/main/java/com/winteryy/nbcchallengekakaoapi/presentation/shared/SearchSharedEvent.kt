package com.winteryy.nbcchallengekakaoapi.presentation.shared

import com.winteryy.nbcchallengekakaoapi.presentation.bookmark.BookmarkListItem

sealed interface SearchSharedEvent {

    data class UpdateBookmark(
        val list: List<BookmarkListItem>
    ): SearchSharedEvent

}