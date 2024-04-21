package com.winteryy.nbcchallengekakaoapi.domain.usecase

import com.winteryy.nbcchallengekakaoapi.domain.entity.DataError
import com.winteryy.nbcchallengekakaoapi.domain.entity.Result
import com.winteryy.nbcchallengekakaoapi.domain.entity.SearchImageEntity

interface SearchUseCase {
    suspend operator fun invoke(query: String): Result<SearchImageEntity, DataError>
}