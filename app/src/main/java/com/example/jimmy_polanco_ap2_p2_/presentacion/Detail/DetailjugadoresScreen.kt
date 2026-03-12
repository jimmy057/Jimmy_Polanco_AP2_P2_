package com.example.jimmy_polanco_ap2_p2_.presentacion.Detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailJugadorScreen(
    jugadorId: Int,
    viewModel: DetailJugadoresViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(jugadorId) {
        viewModel.onEvent(DetailJugadoresUiEvent.LoadJugador, jugadorId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle Jugador") },
                navigationIcon = {
                    IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, contentDescription = null) }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            if(state.isLoading) CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            state.jugador?.let { jugador ->
                Text("ID: ${jugador.jugadorId}")
                Text("Nombre: ${jugador.nombres}")
                Text("Email: ${jugador.email}")
            }
            state.error?.let { Text(it, color = MaterialTheme.colorScheme.error) }
        }
    }
}