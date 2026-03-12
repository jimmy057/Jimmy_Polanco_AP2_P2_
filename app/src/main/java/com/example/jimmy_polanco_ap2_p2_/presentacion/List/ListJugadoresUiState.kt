package com.example.jimmy_polanco_ap2_p2_.presentacion.List

import com.example.jimmy_polanco_ap2_p2_.domain.model.Jugador

data class ListJugadoresUiState(
    val isLoading: Boolean = false,
    val jugadores: List<Jugador> = emptyList(),
    val error: String? = null
)