package com.winteryy.nbcchallengekakaoapi.app.network

import com.winteryy.nbcchallengekakaoapi.data.datasource.remote.api.NetworkService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://dapi.kakao.com"

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor())
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val networkService: NetworkService by lazy {
        retrofit.create(NetworkService::class.java)
    }
}