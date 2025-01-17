package com.lggpmlrg.canitaspadv2_a.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lggpmlrg.canitaspadv2_a.data.NotasRepository
import com.lggpmlrg.canitaspadv2_a.model.Nota
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class NotasScreenViewModel(itemsRepository: NotasRepository): ViewModel()  {
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    init {
        initializeUIState()
    }

    private fun initializeUIState() {
        AppUiState(
            cantidadNotas = 0,
            cantidadTareas = 0,
            cantidadTareasComp = 0
        )
    }

    val busquedaInput = mutableStateOf("")

    val notaUiState: StateFlow<HomeUiState> =
        itemsRepository.getAllItemsStream().map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

}

data class HomeUiState(val itemList: List<Nota> = listOf())