package com.winteryy.nbcchallengekakaoapi.data.datasource.remote

import com.winteryy.nbcchallengekakaoapi.data.model.ImageDocumentResponse
import com.winteryy.nbcchallengekakaoapi.data.model.SearchResponse
import com.winteryy.nbcchallengekakaoapi.data.model.VideoDocumentResponse

interface RemoteDataSource {
    suspend fun getSearchImageResponse(query: String): SearchResponse<ImageDocumentResponse>?
    suspend fun getSearchVideoResponse(query: String): SearchResponse<VideoDocumentResponse>?
}