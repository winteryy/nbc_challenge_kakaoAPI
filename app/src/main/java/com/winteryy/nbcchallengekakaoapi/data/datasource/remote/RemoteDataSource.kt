package com.winteryy.nbcchallengekakaoapi.data.datasource.remote

import com.winteryy.nbcchallengekakaoapi.data.model.SearchResponse

interface RemoteDataSource {
    suspend fun getSearchResponse(query: String): SearchResponse?
}