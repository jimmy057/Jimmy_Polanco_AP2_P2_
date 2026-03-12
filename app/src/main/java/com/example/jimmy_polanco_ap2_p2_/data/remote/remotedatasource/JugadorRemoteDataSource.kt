package com.example.jimmy_polanco_ap2_p2_.data.remote.remotedatasource

import com.example.jimmy_polanco_ap2_p2_.data.remote.Api.ApiJugador
import com.example.jimmy_polanco_ap2_p2_.data.remote.Dto.JugadorRequest
import com.example.jimmy_polanco_ap2_p2_.data.remote.Dto.JugadorResponse
import retrofit2.HttpException
import javax.inject.Inject

class JugadorRemoteDataSource @Inject constructor(
    private val api: ApiJugador
) {

    suspend fun getJugadores(): Result<List<JugadorResponse>> {
        return try {
            val response = api.getJugadores()
            Result.success(response)
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor: ${e.code()}", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }

    suspend fun getJugador(id: Int): Result<JugadorResponse> {
        return try {
            val response = api.getJugador(id)
            Result.success(response)
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor: ${e.code()}", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }

    suspend fun createJugador(jugadorRequest: JugadorRequest): Result<JugadorResponse> {
        return try {
            val response = api.createJugador(jugadorRequest)
            Result.success(response)
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor: ${e.code()}", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }

    suspend fun updateJugador(id: Int, jugadorRequest: JugadorRequest): Result<JugadorResponse> {
        return try {
            val response = api.updateJugador(id, jugadorRequest)
            Result.success(response)
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor: ${e.code()}", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }
}