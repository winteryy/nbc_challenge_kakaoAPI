package com.winteryy.nbcchallengekakaoapi.presentation.shared

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.winteryy.nbcchallengekakaoapi.presentation.bookmark.BookmarkListItem
import com.winteryy.nbcchallengekakaoapi.presentation.search.SearchListItem
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SearchSharedViewModel: ViewModel() {

    private val _event = MutableSharedFlow<SearchSharedEvent>(replay = 1)
    val event: SharedFlow<SearchSharedEvent> = _event.asSharedFlow()

    fun updateBookmarkItems(list: List<SearchListItem>) = viewModelScope.launch {
        list.filter {
            it.isBookmarked
        }.map {
            BookmarkListItem(
                title = it.title,
                thumbnailUrl = it.thumbnailUrl,
                dateTime = it.dateTime,
                isBookmarked = it.isBookmarked
            )
        }.also {
            _event.emit(SearchSharedEvent.UpdateBookmark(it))
        }
    }

    fun updateSearchItem(id: String, list: List<BookmarkListItem>) = viewModelScope.launch {
        _event.emit(SearchSharedEvent.UpdateBookmark(
            list.filter { it.thumbnailUrl != id }
        ))
        _event.emit(SearchSharedEvent.UpdateSearch(id))
    }
}