package com.winteryy.nbcchallengekakaoapi.data.model

import com.winteryy.nbcchallengekakaoapi.domain.entity.Meta

fun ResponseMeta.toMeta() = Meta(
    totalCount = totalCount,
    pageableCount = pageableCount,
    isEnd = isEnd
)