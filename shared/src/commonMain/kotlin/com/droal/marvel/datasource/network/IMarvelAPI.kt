package com.droal.marvel.datasource.network

import com.droal.marvel.datasource.network.model.CharacterDto
import com.droal.marvel.domain.Character
import com.droal.marvel.util.Response
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json

abstract class  IMarvelAPI {
    abstract suspend fun getCharacters(): Response<List<Character>>
}