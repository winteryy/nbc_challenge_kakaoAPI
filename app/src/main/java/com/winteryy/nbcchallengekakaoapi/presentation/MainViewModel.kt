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

    fun searchImage(query: String) = viewModelScope.launch {
        when(val result = searchUseCase(query)) {
            is Result.Success -> {
                _searchList.value = result.data.toModel().searchImageList
            }
            is Result.Error -> Log.d("MainViewModel", "fail")
        }
    }
}

class MainViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            UseCaseInjector.searchUseCase
        ) as T
    }
}