package com.winteryy.nbcchallengekakaoapi.data.repository

import com.winteryy.nbcchallengekakaoapi.data.datasource.remote.RemoteDataSource
import com.winteryy.nbcchallengekakaoapi.data.model.toMeta
import com.winteryy.nbcchallengekakaoapi.domain.entity.DataError
import com.winteryy.nbcchallengekakaoapi.domain.entity.ImageItem
import com.winteryy.nbcchallengekakaoapi.domain.entity.Meta
import com.winteryy.nbcchallengekakaoapi.domain.entity.Result
import com.winteryy.nbcchallengekakaoapi.domain.entity.SearchImageEntity
import com.winteryy.nbcchallengekakaoapi.domain.repository.SearchRepository

class SearchRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): SearchRepository{
    override suspend fun searchImage(query: String): Result<SearchImageEntity, DataError> {
        val response = remoteDataSource.getSearchResponse(query)

        return if(response==null) {
            Result.Error(DataError.NETWORK_ERROR)
        }else {
            val entity = SearchImageEntity(
                meta = response.meta.toMeta(),
                searchImageList = response.documents.map {
                    ImageItem(
                        collection = it.collection,
                        thumbnailUrl = it.thumbnailUrl,
                        imageUrl = it.imageUrl,
                        width = it.width,
                        height = it.height,
                        siteName = it.siteName,
                        docUrl = it.docUrl,
                        datetime = it.datetime,
                        isBookmarked = false //나중에 로컬에서 받아오나..?
                    )
                }
            )
            Result.Success(entity)
        }
    }
}