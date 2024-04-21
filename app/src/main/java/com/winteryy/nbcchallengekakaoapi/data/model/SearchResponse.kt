package com.winteryy.nbcchallengekakaoapi.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class SearchResponse(
    @SerializedName("meta")
    val meta: ResponseMeta,
    @SerializedName("documents")
    val documents: List<Document>,
)

data class ResponseMeta(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("pageable_count")
    val pageableCount: Int,
    @SerializedName("is_end")
    val isEnd: Boolean,
)

data class Document(
    @SerializedName("collection")
    val collection: String,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("display_sitename")
    val siteName: String,
    @SerializedName("doc_url")
    val docUrl: String,
    @SerializedName("datetime")
    val datetime: Date,
)
