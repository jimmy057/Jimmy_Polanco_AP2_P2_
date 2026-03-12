package com.example.jimmy_polanco_ap2_p2_.presentacion.List

sealed interface ListJugadoresUiEvent {
    data object Load : ListJugadoresUiEvent
}
