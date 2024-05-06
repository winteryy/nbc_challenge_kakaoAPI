package com.winteryy.nbcchallengekakaoapi.data.repository

import com.winteryy.nbcchallengekakaoapi.data.datasource.remote.RemoteDataSource
import com.winteryy.nbcchallengekakaoapi.data.model.toEntity
import com.winteryy.nbcchallengekakaoapi.data.model.toImageEntity
import com.winteryy.nbcchallengekakaoapi.data.model.toVideoEntity
import com.winteryy.nbcchallengekakaoapi.domain.entity.SearchItemEntity
import com.winteryy.nbcchallengekakaoapi.domain.entity.SearchResultEntity
import com.winteryy.nbcchallengekakaoapi.domain.repository.SearchRepository

class SearchRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): SearchRepository{
    override suspend fun searchImage(query: String): SearchResultEntity<SearchItemEntity.ImageItemEntity> {
        val response = remoteDataSource.getSearchImageResponse(query)

        return response?.toImageEntity() ?: throw Error("Network Error")
    }

    override suspend fun searchVideo(query: String): SearchResultEntity<SearchItemEntity.VideoItemEntity> {
        val response = remoteDataSource.getSearchVideoResponse(query)

        return response?.toVideoEntity() ?: throw Error("Network Error")
    }
}