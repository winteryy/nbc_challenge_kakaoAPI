package com.winteryy.nbcchallengekakaoapi.domain.repository

import com.winteryy.nbcchallengekakaoapi.domain.entity.DataError
import com.winteryy.nbcchallengekakaoapi.domain.entity.Result
import com.winteryy.nbcchallengekakaoapi.domain.entity.SearchImageEntity

interface SearchRepository {
    suspend fun searchImage(query: String): Result<SearchImageEntity, DataError>
}