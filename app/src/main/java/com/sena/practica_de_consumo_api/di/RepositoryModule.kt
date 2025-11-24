package com.sena.practica_de_consumo_api.di

import androidx.annotation.Size
import com.sena.practica_de_consumo_api.data.PokemonApi
import com.sena.practica_de_consumo_api.data.repository.PokemonRepositoryImpl
import com.sena.practica_de_consumo_api.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(value = [SingletonComponent::class])
object RepositoryModule {

    @Provides
    @Singleton
    fun providePokemonRepository(
        PokemonRepositoryImpl: PokemonRepositoryImpl
    ): PokemonRepository {
        return PokemonRepositoryImpl
    }
}