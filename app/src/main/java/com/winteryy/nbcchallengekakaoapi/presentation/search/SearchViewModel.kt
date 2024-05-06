package com.winteryy.nbcchallengekakaoapi.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.winteryy.nbcchallengekakaoapi.app.di.UseCaseInjector
import com.winteryy.nbcchallengekakaoapi.domain.entity.IntegratedSearchResultEntity
import com.winteryy.nbcchallengekakaoapi.domain.entity.SearchItemEntity
import com.winteryy.nbcchallengekakaoapi.domain.usecase.SearchUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchListUiState.init())
    val uiState: StateFlow<SearchListUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<SearchListEvent>()
    val event: SharedFlow<SearchListEvent> = _event.asSharedFlow()

    fun onSearch(query: String) = viewModelScope.launch {
        showLoading(true)
        runCatching {
            val items = convertItems(searchUseCase(query))

            _uiState.update { prevState ->
                prevState.copy(
                    list = items,
                    isLoading = false
                )
            }
        }.onFailure {
            showLoading(false)
        }
    }

    fun onBookmark(item: SearchListItem) = viewModelScope.launch {
        val newList = uiState.value.list.toMutableList()

        val position = newList.indexOfFirst { it.thumbnailUrl == item.thumbnailUrl }

        _uiState.update { prev ->
            prev.copy(
                list = newList.also {
                    it[position] = when(item) {
                        is SearchListItem.SearchImageItem -> item.copy(isBookmarked = item.isBookmarked.not())
                        is SearchListItem.SearchVideoItem -> item.copy(isBookmarked = item.isBookmarked.not())
                    }
                }
            )
        }

        _event.emit(SearchListEvent.UpdateBookmark(uiState.value.list))
    }

    private fun convertItems(
        searchResult: IntegratedSearchResultEntity
    ): List<SearchListItem> = searchResult.documents.map {
        when(it) {
            is SearchItemEntity.ImageItemEntity -> {
                SearchListItem.SearchImageItem(
                    title = it.siteName,
                    thumbnailUrl = it.thumbnailUrl,
                    dateTime = it.dateTime,
                    isBookmarked = it.isBookmarked
                )
            }

            is SearchItemEntity.VideoItemEntity -> {
                SearchListItem.SearchVideoItem(
                    title = it.title,
                    thumbnailUrl = it.thumbnail,
                    dateTime = it.dateTime,
                    isBookmarked = it.isBookmarked
                )
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        _uiState.update { prevState ->
            prevState.copy(
                isLoading = isLoading
            )
        }
    }
}

class SearchViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel(
            UseCaseInjector.searchUseCase
        ) as T
    }
}