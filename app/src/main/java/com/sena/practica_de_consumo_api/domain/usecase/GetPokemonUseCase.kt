package com.sena.practica_de_consumo_api.domain.usecase

import com.sena.practica_de_consumo_api.domain.repository.PokemonRepository
import jakarta.inject.Inject

class GetPokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(): List<Pokemon> {
        return pokemonRepository.getPokemon()
    }
}