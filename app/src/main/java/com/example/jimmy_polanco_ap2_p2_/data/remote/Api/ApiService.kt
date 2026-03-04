package com.example.jimmy_polanco_ap2_p2_.data.remote.Api

import android.graphics.ColorSpace
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("endpoint")
    suspend fun getData(): Response<List<ColorSpace.Model>>

}