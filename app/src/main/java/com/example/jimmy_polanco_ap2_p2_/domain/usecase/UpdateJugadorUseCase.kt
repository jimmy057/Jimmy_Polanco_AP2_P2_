package com.example.jimmy_polanco_ap2_p2_.domain.usecase

import com.example.jimmy_polanco_ap2_p2_.domain.model.Jugador
import com.example.jimmy_polanco_ap2_p2_.domain.repository.JugadorRepository
import com.example.jimmy_polanco_ap2_p2_.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateJugadorUseCase @Inject constructor(
    private val repository: JugadorRepository
) {
    operator fun invoke(id: Int, jugador: Jugador): Flow<Resource<Jugador>> =
        repository.updateJugador(id, jugador)
}