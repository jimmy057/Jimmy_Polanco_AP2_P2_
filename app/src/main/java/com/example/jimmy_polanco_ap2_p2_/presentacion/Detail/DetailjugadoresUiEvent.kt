package com.example.jimmy_polanco_ap2_p2_.presentacion.Detail

sealed interface DetailJugadoresUiEvent {
    data object LoadJugador : DetailJugadoresUiEvent
}