package com.winteryy.nbcchallengekakaoapi.domain.usecase

import com.winteryy.nbcchallengekakaoapi.domain.entity.IntegratedSearchResultEntity

interface SearchUseCase {
    suspend operator fun invoke(query: String): IntegratedSearchResultEntity
}