package com.sena.practica_de_consumo_api.domain.repository

interface PokemonRepository {

    suspend fun getPokemon(): List<Pokemon>
}