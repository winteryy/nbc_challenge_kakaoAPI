package com.winteryy.nbcchallengekakaoapi.domain.usecase

import com.winteryy.nbcchallengekakaoapi.domain.entity.DataError
import com.winteryy.nbcchallengekakaoapi.domain.entity.Result
import com.winteryy.nbcchallengekakaoapi.domain.entity.SearchImageEntity
import com.winteryy.nbcchallengekakaoapi.domain.repository.SearchRepository

class SearchUseCaseImpl(
    private val searchRepository: SearchRepository
): SearchUseCase {
    override suspend fun invoke(query: String): Result<SearchImageEntity, DataError> {
        return searchRepository.searchImage(query)
    }
}