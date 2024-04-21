package com.winteryy.nbcchallengekakaoapi.app.di

import com.winteryy.nbcchallengekakaoapi.domain.usecase.SearchUseCaseImpl

object UseCaseInjector {
    val searchUseCase by lazy {
        SearchUseCaseImpl(RepositoryInjector.searchRepository)
    }
}