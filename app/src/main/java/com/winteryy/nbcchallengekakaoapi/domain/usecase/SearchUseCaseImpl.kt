package com.winteryy.nbcchallengekakaoapi.domain.usecase

import com.winteryy.nbcchallengekakaoapi.domain.entity.IntegratedSearchResultEntity
import com.winteryy.nbcchallengekakaoapi.domain.repository.SearchRepository

class SearchUseCaseImpl(
    private val searchRepository: SearchRepository
): SearchUseCase {
    override suspend fun invoke(query: String): IntegratedSearchResultEntity {

        val searchImageResult = searchRepository.searchImage(query)
        val searchVideoResult = searchRepository.searchVideo(query)

        val itemDocuments = (searchImageResult.documents + searchVideoResult.documents)
            .sortedByDescending { it.dateTime }

        return IntegratedSearchResultEntity(
            imageMeta = searchImageResult.meta,
            videoMeta = searchVideoResult.meta,
            documents = itemDocuments
        )
    }
}