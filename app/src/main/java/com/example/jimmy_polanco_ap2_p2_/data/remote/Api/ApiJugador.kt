package com.example.jimmy_polanco_ap2_p2_.data.remote.Api

import com.example.jimmy_polanco_ap2_p2_.data.remote.Dto.JugadorRequest
import com.example.jimmy_polanco_ap2_p2_.data.remote.Dto.JugadorResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiJugador {
    @GET("api/Jugadores")
    suspend fun getJugadores(): List<JugadorResponse>

    @GET("api/Jugadores/{id}")
    suspend fun getJugador(@Path("id") id: Int): JugadorResponse

    @POST("api/Jugadores")
    suspend fun createJugador(@Body jugador: JugadorRequest): JugadorResponse

    @PUT("api/Jugadores/{id}")
    suspend fun updateJugador(@Path("id") id: Int, @Body jugador: JugadorRequest): JugadorResponse
}