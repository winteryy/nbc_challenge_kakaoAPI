package com.winteryy.nbcchallengekakaoapi.data.model

import com.winteryy.nbcchallengekakaoapi.domain.entity.MetaEntity
import com.winteryy.nbcchallengekakaoapi.domain.entity.SearchItemEntity
import com.winteryy.nbcchallengekakaoapi.domain.entity.SearchResultEntity

fun SearchResponse<ImageDocumentResponse>.toImageEntity() = SearchResultEntity(
    meta = meta.toEntity(),
    documents = documents.map { it.toEntity() }
)

fun SearchResponse<VideoDocumentResponse>.toVideoEntity() = SearchResultEntity(
    meta = meta.toEntity(),
    documents = documents.map { it.toEntity() }
)

fun MetaResponse.toEntity() = MetaEntity(
    totalCount = totalCount,
    pageableCount = pageableCount,
    isEnd = isEnd
)

fun ImageDocumentResponse.toEntity() = SearchItemEntity.ImageItemEntity(
    collection = collection,
    thumbnailUrl = thumbnailUrl,
    imageUrl = imageUrl,
    width = width,
    height = height,
    siteName = siteName,
    docUrl = docUrl,
    isBookmarked = false,
    dateTime = datetime
)

fun VideoDocumentResponse.toEntity() = SearchItemEntity.VideoItemEntity(
    title = title,
    url = url,
    playTime = playTime,
    thumbnail = thumbnail,
    author = author,
    isBookmarked = false,
    dateTime = datetime,
)
