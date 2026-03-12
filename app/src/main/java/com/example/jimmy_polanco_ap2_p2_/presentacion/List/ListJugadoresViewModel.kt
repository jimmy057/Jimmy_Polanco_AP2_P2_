package com.example.jimmy_polanco_ap2_p2_.presentacion.List

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jimmy_polanco_ap2_p2_.domain.model.Jugador
import com.example.jimmy_polanco_ap2_p2_.domain.usecase.CreateJugadorUseCase
import com.example.jimmy_polanco_ap2_p2_.domain.usecase.GetJugadorUseCase
import com.example.jimmy_polanco_ap2_p2_.domain.usecase.GetJugadoresUseCase
import com.example.jimmy_polanco_ap2_p2_.domain.usecase.UpdateJugadorUseCase
import com.example.jimmy_polanco_ap2_p2_.presentacion.Detail.DetailJugadoresUiState
import com.example.jimmy_polanco_ap2_p2_.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListJugadoresViewModel @Inject constructor(
    private val getJugadoresUseCase: GetJugadoresUseCase,
    private val getJugadorUseCase: GetJugadorUseCase,
    private val createJugadorUseCase: CreateJugadorUseCase,
    private val updateJugadorUseCase: UpdateJugadorUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ListJugadoresUiState())
    val state: StateFlow<ListJugadoresUiState> = _state.asStateFlow()

    private val _detailState = MutableStateFlow(DetailJugadoresUiState())
    val detailState: StateFlow<DetailJugadoresUiState> = _detailState.asStateFlow()

    init { loadJugadores() }

    fun onEvent(event: ListJugadoresUiEvent) {
        when(event) {
            ListJugadoresUiEvent.Load -> loadJugadores()
        }
    }

    fun loadJugadores() {
        viewModelScope.launch {
            getJugadoresUseCase().collect { result ->
                when(result) {
                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                    is Resource.Success -> _state.update { it.copy(isLoading = false, jugadores = result.data ?: emptyList()) }
                    is Resource.Error -> _state.update { it.copy(isLoading = false, error = result.message) }
                }
            }
        }
    }

    fun loadJugador(id: Int) {
        viewModelScope.launch {
            getJugadorUseCase(id).collect { result ->
                when(result) {
                    is Resource.Loading -> _detailState.update { it.copy(isLoading = true) }
                    is Resource.Success -> _detailState.update { it.copy(isLoading = false, jugador = result.data) }
                    is Resource.Error -> _detailState.update { it.copy(isLoading = false, error = result.message) }
                }
            }
        }
    }

    fun createJugador(nombre: String, email: String, onComplete: () -> Unit) {
        viewModelScope.launch {
            val jugador = Jugador(0, nombre, email)
            createJugadorUseCase(jugador).collect { result ->
                if(result is Resource.Success) onComplete()
                else if(result is Resource.Error) _detailState.update { it.copy(error = result.message) }
            }
        }
    }

    fun updateJugador(id: Int, nombre: String, email: String, onComplete: () -> Unit) {
        viewModelScope.launch {
            val jugador = Jugador(id, nombre, email)
            updateJugadorUseCase(id, jugador).collect { result ->
                if(result is Resource.Success) onComplete()
                else if(result is Resource.Error) _detailState.update { it.copy(error = result.message) }
            }
        }
    }
}