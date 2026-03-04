package com.example.jimmy_polanco_ap2_p2_.di

import com.example.jimmy_polanco_ap2_p2_.data.remote.Api.ApiService
import com.example.jimmy_polanco_ap2_p2_.data.remote.remotedatasource.RemoteDataSource
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object AppModule {
    private const val BASE_URL = "https://tu-api-aqui.com/"

    private val moshi: Moshi by lazy {
        Moshi.Builder().build()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    val remoteDataSource: RemoteDataSource by lazy {
        RemoteDataSource(apiService)
    }
}