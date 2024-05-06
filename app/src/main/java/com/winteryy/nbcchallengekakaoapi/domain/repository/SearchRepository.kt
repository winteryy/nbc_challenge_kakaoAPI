package com.winteryy.nbcchallengekakaoapi.domain.repository

import com.winteryy.nbcchallengekakaoapi.domain.entity.SearchItemEntity
import com.winteryy.nbcchallengekakaoapi.domain.entity.SearchResultEntity

interface SearchRepository {
    suspend fun searchImage(query: String): SearchResultEntity<SearchItemEntity.ImageItemEntity>
    suspend fun searchVideo(query: String): SearchResultEntity<SearchItemEntity.VideoItemEntity>
}