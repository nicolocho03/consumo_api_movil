package com.sena.practica_de_consumo_api.presentation.user

import com.sena.practica_de_consumo_api.domain.model.Pokemon

data class PokemonUiState(
    val isLoading: Boolean = false,
    val users: List<Pokemon> = emptyList(),
    val errorMessage: String? = null
)
