package com.example.jimmy_polanco_ap2_p2_.presentacion.navigation

sealed class Routes(val route: String) {

    object ListJugadores : Routes("list_jugadores")

    object DetailJugador : Routes("detail_jugador/{jugadorId}") {
        fun createRoute(jugadorId: Int) = "detail_jugador/$jugadorId"
    }

    object FormJugador : Routes("form_jugador/{jugadorId}") {
        fun createRoute(jugadorId: Int = -1) = "form_jugador/$jugadorId"
    }
}