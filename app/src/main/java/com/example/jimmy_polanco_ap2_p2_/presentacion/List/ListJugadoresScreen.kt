package com.example.jimmy_polanco_ap2_p2_.presentacion.List

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jimmy_polanco_ap2_p2_.domain.model.Jugador

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListJugadoresScreen(
    viewModel: ListJugadoresViewModel = hiltViewModel(),
    onAddJugador: () -> Unit,
    onEditJugador: (Int) -> Unit
) {
    val state by viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Jugadores Snake") },
                actions = {
                    IconButton(onClick = onAddJugador) { Icon(Icons.Default.Add, contentDescription = null) }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {

            if(state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            state.error?.let { Text(it, color = MaterialTheme.colorScheme.error) }

            LazyColumn {
                items(state.jugadores) { jugador ->
                    JugadorItem(jugador) { onEditJugador(jugador.jugadorId) }
                    Spacer(modifier = Modifier.height(8.dp))
                }
                item { Text("Total jugadores: ${state.jugadores.size}") }
            }
        }
    }
}

@Composable
fun JugadorItem(jugador: Jugador, onClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().clickable { onClick() }) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("ID: ${jugador.jugadorId}")
            Text("Nombre: ${jugador.nombres}")
            Text("Email: ${jugador.email}")
        }
    }
}