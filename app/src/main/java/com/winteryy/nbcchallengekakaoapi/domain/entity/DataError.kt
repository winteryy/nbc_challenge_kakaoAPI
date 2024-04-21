package com.winteryy.nbcchallengekakaoapi.domain.entity

//TODO 필요하면 DataError에서 Network랑 Local로 분화하기
//TODO 하위 에러 세분화?
enum class DataError: Error {
    NETWORK_ERROR, UNKNOWN_ERROR
}