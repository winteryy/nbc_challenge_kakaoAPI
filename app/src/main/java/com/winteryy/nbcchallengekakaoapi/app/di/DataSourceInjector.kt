package com.winteryy.nbcchallengekakaoapi.app.di

import com.winteryy.nbcchallengekakaoapi.app.network.RetrofitClient
import com.winteryy.nbcchallengekakaoapi.data.datasource.remote.RemoteDataSourceImpl

object DataSourceInjector {
    val remoteDataSource by lazy {
        RemoteDataSourceImpl(RetrofitClient.networkService)
    }
}