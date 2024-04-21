package com.winteryy.nbcchallengekakaoapi.presentation.model

import com.winteryy.nbcchallengekakaoapi.domain.entity.ImageItem
import com.winteryy.nbcchallengekakaoapi.domain.entity.Meta
import com.winteryy.nbcchallengekakaoapi.domain.entity.SearchImageEntity

fun SearchImageEntity.toModel() = SearchImageResult(
    meta = this.meta.toResultMeta(),
    searchImageList = this.searchImageList.map { it.toListItem() }
)

fun Meta.toResultMeta() = ResultMeta(
    totalCount = totalCount,
    pageableCount = pageableCount,
    isEnd = isEnd
)

fun ImageItem.toListItem() = ImageListItem(
    collection = collection,
    thumbnailUrl = thumbnailUrl,
    imageUrl = imageUrl,
    width = width,
    height = height,
    siteName = siteName,
    docUrl = docUrl,
    datetime = datetime,
    isBookmarked = isBookmarked
)