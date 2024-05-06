package com.winteryy.nbcchallengekakaoapi.presentation.search

data class SearchListUiState(
    val list: List<SearchListItem>,
    val isLoading: Boolean
) {

    companion object {
        fun init() = SearchListUiState(
            list = emptyList(),
            isLoading = false
        )
    }

}
