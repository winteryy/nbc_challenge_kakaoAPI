package com.winteryy.nbcchallengekakaoapi.data.datasource.remote

import com.winteryy.nbcchallengekakaoapi.data.datasource.remote.api.NetworkService
import com.winteryy.nbcchallengekakaoapi.data.model.ImageDocumentResponse
import com.winteryy.nbcchallengekakaoapi.data.model.SearchResponse
import com.winteryy.nbcchallengekakaoapi.data.model.VideoDocumentResponse

class RemoteDataSourceImpl(
    private val networkService: NetworkService
): RemoteDataSource {

    override suspend fun getSearchImageResponse(query: String): SearchResponse<ImageDocumentResponse>? {
        return try {
            networkService.searchImage(query = query)
        }catch (e: Exception) {
            //TODO 예외 핸들링
            null
        }
    }

    override suspend fun getSearchVideoResponse(query: String): SearchResponse<VideoDocumentResponse>? {
        return try {
            networkService.searchVideo(query = query)
        }catch (e: Exception) {
            //TODO 예외 핸들링
            null
        }
    }

}