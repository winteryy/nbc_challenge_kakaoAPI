package com.winteryy.nbcchallengekakaoapi.data.datasource.remote.api

import com.winteryy.nbcchallengekakaoapi.data.model.ImageDocumentResponse
import com.winteryy.nbcchallengekakaoapi.data.model.SearchResponse
import com.winteryy.nbcchallengekakaoapi.data.model.VideoDocumentResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET("v2/search/image")
    suspend fun searchImage(
        @Query("query") query: String,
        @Query("sort") sort: String = "accuracy",
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 80
    ): SearchResponse<ImageDocumentResponse>

    @GET("v2/search/vclip")
    suspend fun searchVideo(
        @Query("query") query: String,
        @Query("sort") sort: String = "accuracy",
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 15
    ): SearchResponse<VideoDocumentResponse>
}