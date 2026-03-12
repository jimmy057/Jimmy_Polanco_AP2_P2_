package com.example.jimmy_polanco_ap2_p2_.presentacion.Detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jimmy_polanco_ap2_p2_.presentacion.List.ListJugadoresViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormJugadorScreen(
    jugadorId: Int? = null,
    viewModel: ListJugadoresViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    val detailState by viewModel.detailState.collectAsState()

    LaunchedEffect(jugadorId) {
        jugadorId?.let { viewModel.loadJugador(it) }
    }

    LaunchedEffect(detailState.jugador) {
        detailState.jugador?.let { jugador ->
            nombre = jugador.nombres
            email = jugador.email
        }
        error = detailState.error
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if(jugadorId == null) "Crear Jugador" else "Editar Jugador") },
                navigationIcon = {
                    IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, contentDescription = null) }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") }, isError = nombre.isBlank(), modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, isError = !email.contains("@"), modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(16.dp))
            error?.let { Text(it, color = MaterialTheme.colorScheme.error); Spacer(modifier = Modifier.height(8.dp)) }
            Button(onClick = {
                if(nombre.isBlank() || !email.contains("@")) { error = "Por favor llena todos los campos"; return@Button }
                if(jugadorId == null) viewModel.createJugador(nombre, email) { onBack() }
                else viewModel.updateJugador(jugadorId, nombre, email) { onBack() }
            }, modifier = Modifier.fillMaxWidth()) {
                Text(if(jugadorId == null) "Crear" else "Actualizar")
            }
        }
    }
}