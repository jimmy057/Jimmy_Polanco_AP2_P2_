package com.example.jimmy_polanco_ap2_p2_.data.remote.Dto

data class JugadorResponse(
    val jugadorId: Int,
    val nombres: String,
    val email: String
) {
    fun toDomain() = com.example.jimmy_polanco_ap2_p2_.domain.model.Jugador(
        jugadorId = jugadorId,
        nombres = nombres,
        email = email
    )
}