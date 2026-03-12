package com.example.jimmy_polanco_ap2_p2_.domain.repository

import com.example.jimmy_polanco_ap2_p2_.domain.model.Jugador
import com.example.jimmy_polanco_ap2_p2_.util.Resource
import kotlinx.coroutines.flow.Flow

interface JugadorRepository {
    fun getJugadores(): Flow<Resource<List<Jugador>>>
    fun getJugador(id: Int): Flow<Resource<com.example.jimmy_polanco_ap2_p2_.domain.model.Jugador>>
    fun createJugador(jugador: com.example.jimmy_polanco_ap2_p2_.domain.model.Jugador): Flow<Resource<com.example.jimmy_polanco_ap2_p2_.domain.model.Jugador>>
    fun updateJugador(id: Int, jugador: com.example.jimmy_polanco_ap2_p2_.domain.model.Jugador): Flow<Resource<com.example.jimmy_polanco_ap2_p2_.domain.model.Jugador>>
}