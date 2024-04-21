package com.winteryy.nbcchallengekakaoapi.data.datasource.remote

import com.winteryy.nbcchallengekakaoapi.data.datasource.remote.api.NetworkService
import com.winteryy.nbcchallengekakaoapi.data.model.SearchResponse

class RemoteDataSourceImpl(
    private val networkService: NetworkService
): RemoteDataSource {
    override suspend fun getSearchResponse(query: String): SearchResponse? {
        return try {
            networkService.searchImage(query = query)
        }catch (e: Exception) {
            //todo 예외 핸들링
            null
        }
    }
}