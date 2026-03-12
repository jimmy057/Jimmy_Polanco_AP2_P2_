package com.example.jimmy_polanco_ap2_p2_.data.repository

import com.example.jimmy_polanco_ap2_p2_.data.remote.remotedatasource.JugadorRemoteDataSource
import com.example.jimmy_polanco_ap2_p2_.domain.model.Jugador
import com.example.jimmy_polanco_ap2_p2_.domain.repository.JugadorRepository
import com.example.jimmy_polanco_ap2_p2_.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class JugadorRepositoryImpl @Inject constructor(
    private val remoteDataSource: JugadorRemoteDataSource
) : JugadorRepository {

    override fun getJugadores(): Flow<Resource<List<Jugador>>> = flow {
        emit(Resource.Loading())

        val response = remoteDataSource.getJugadores()
        response.onSuccess { list ->
            emit(Resource.Success(list.map { it.toDomain() }))
        }.onFailure { error ->
            emit(Resource.Error(error.message ?: "Error desconocido"))
        }
    }

    override fun getJugador(id: Int): Flow<Resource<Jugador>> = flow {
        emit(Resource.Loading())

        val response = remoteDataSource.getJugador(id)
        response.onSuccess { jugador ->
            emit(Resource.Success(jugador.toDomain()))
        }.onFailure { error ->
            emit(Resource.Error(error.message ?: "Error desconocido"))
        }
    }

    override fun createJugador(jugador: Jugador): Flow<Resource<Jugador>> = flow {
        emit(Resource.Loading())

        val response = remoteDataSource.createJugador(jugador.toRequest())
        response.onSuccess { jugadorResponse ->
            emit(Resource.Success(jugadorResponse.toDomain()))
        }.onFailure { error ->
            emit(Resource.Error(error.message ?: "Error desconocido"))
        }
    }

    override fun updateJugador(id: Int, jugador: Jugador): Flow<Resource<Jugador>> = flow {
        emit(Resource.Loading())

        val response = remoteDataSource.updateJugador(id, jugador.toRequest())
        response.onSuccess { jugadorResponse ->
            emit(Resource.Success(jugadorResponse.toDomain()))
        }.onFailure { error ->
            emit(Resource.Error(error.message ?: "Error desconocido"))
        }
    }

    private fun Jugador.toRequest() = com.example.jimmy_polanco_ap2_p2_.data.remote.Dto.JugadorRequest(
        nombres = nombres,
        email = email
    )
}