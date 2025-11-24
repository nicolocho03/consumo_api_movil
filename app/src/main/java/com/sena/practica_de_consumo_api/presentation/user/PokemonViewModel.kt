package com.sena.consumoapi.presentation.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sena.practica_de_consumo_api.domain.usecase.GetPokemonUserCase
import com.sena.practica_de_consumo_api.presentation.pokemon.PokemonUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonUserCase: GetPokemonUserCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(PokemonUiState())

    // StateFlow inmutable expuesto a la UI
    val uiState: StateFlow<PokemonUiState> = _uiState.asStateFlow()

    init {

        loadPokemons()
    }

    private fun loadPokemons() {
        viewModelScope.launch {
            // Estado: Cargando (Se resetea el error anterior)
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null // <--- DIFERENCIA 1: Reinicia el mensaje de error
            )

            try {
                val pokemons = getPokemonUserCase()

                // Estado: Éxito (cargó Pokemons)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    pokemon = pokemons,
                    errorMessage = null
                )
            } catch (e: Exception) {
                e.printStackTrace() // <--- DIFERENCIA 2: Imprime la traza del error

                // Estado: Error
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    // <--- DIFERENCIA 3: Mensaje de error más detallado
                    errorMessage = e.message ?: "Ocurrió un error al cargar los Pokemons"
                )
            }
        }
    }
}