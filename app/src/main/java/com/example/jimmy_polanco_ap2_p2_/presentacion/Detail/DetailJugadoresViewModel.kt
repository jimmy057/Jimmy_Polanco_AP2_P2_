package com.example.jimmy_polanco_ap2_p2_.presentacion.Detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jimmy_polanco_ap2_p2_.domain.usecase.GetJugadorUseCase
import com.example.jimmy_polanco_ap2_p2_.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailJugadoresViewModel @Inject constructor(
    private val getJugadorUseCase: GetJugadorUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DetailJugadoresUiState())
    val state: StateFlow<DetailJugadoresUiState> = _state.asStateFlow()

    fun onEvent(event: DetailJugadoresUiEvent, jugadorId: Int) {
        when(event) {
            DetailJugadoresUiEvent.LoadJugador -> loadJugador(jugadorId)
        }
    }

    fun loadJugador(id: Int) {
        viewModelScope.launch {
            getJugadorUseCase(id).collect { result ->
                when(result) {
                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                    is Resource.Success -> _state.update { it.copy(isLoading = false, jugador = result.data) }
                    is Resource.Error -> _state.update { it.copy(isLoading = false, error = result.message) }
                }
            }
        }
    }
}