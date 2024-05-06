package com.winteryy.nbcchallengekakaoapi.presentation.bookmark

data class BookmarkListUiState(
    val list: List<BookmarkListItem>,
    val isLoading: Boolean
) {

    companion object {
        fun init() = BookmarkListUiState(
            list = emptyList(),
            isLoading = false
        )
    }

}
