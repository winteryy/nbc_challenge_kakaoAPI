package com.winteryy.nbcchallengekakaoapi.app.di

import com.winteryy.nbcchallengekakaoapi.data.repository.SearchRepositoryImpl

object RepositoryInjector {
    val searchRepository by lazy {
        SearchRepositoryImpl(DataSourceInjector.remoteDataSource)
    }
}