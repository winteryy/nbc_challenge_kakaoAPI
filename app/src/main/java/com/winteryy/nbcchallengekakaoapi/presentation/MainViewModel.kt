package com.winteryy.nbcchallengekakaoapi.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.winteryy.nbcchallengekakaoapi.app.di.UseCaseInjector
import com.winteryy.nbcchallengekakaoapi.domain.entity.Result
import com.winteryy.nbcchallengekakaoapi.domain.usecase.SearchUseCase
import com.winteryy.nbcchallengekakaoapi.presentation.model.ImageListItem
import com.winteryy.nbcchallengekakaoapi.presentation.model.toModel
import kotlinx.coroutines.launch

class MainViewModel(
    private val searchUseCase: SearchUseCase
): ViewModel() {

    private var _searchList = MutableLiveData<List<ImageListItem>>(emptyList())
    val searchList: LiveData<List<ImageListItem>> get() = _searchList

    private var _bookmarkList = MutableLiveData<List<ImageListItem>>(emptyList())
    val bookmarkList: LiveData<List<ImageListItem>> get() = _bookmarkList

    fun searchImage(query: String) = viewModelScope.launch {
        when(val result = searchUseCase(query)) {
            is Result.Success -> {
                _searchList.value = result.data.toModel().searchImageList
                _bookmarkList.value = _searchList.value!!.filter {
                    it.isBookmarked
                }
            }
            is Result.Error -> Log.d("MainViewModel", "fail")
        }
    }

    fun updateItem(imageListItem: ImageListItem) {
        val newBookmarkList = mutableListOf<ImageListItem>()
        _searchList.value = _searchList.value?.map {
            if(it.imageUrl==imageListItem.imageUrl) {
                if(imageListItem.isBookmarked) {
                    newBookmarkList.add(imageListItem)
                }
                imageListItem
            }else {
                if(it.isBookmarked) {
                    newBookmarkList.add(it)
                }
                it
            }
        }
        _bookmarkList.value = newBookmarkList
    }
}

class MainViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            UseCaseInjector.searchUseCase
        ) as T
    }
}