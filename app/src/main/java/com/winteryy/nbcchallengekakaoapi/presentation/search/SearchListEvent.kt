package com.winteryy.nbcchallengekakaoapi.presentation.search

sealed interface SearchListEvent {

    data class UpdateBookmark(
        val list: List<SearchListItem>
    ): SearchListEvent

}