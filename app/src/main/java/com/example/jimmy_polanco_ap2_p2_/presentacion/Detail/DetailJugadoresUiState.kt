package com.example.jimmy_polanco_ap2_p2_.presentacion.Detail

import com.example.jimmy_polanco_ap2_p2_.domain.model.Jugador

data class DetailJugadoresUiState(
    val isLoading: Boolean = false,
    val jugador: Jugador? = null,
    val error: String? = null
)
