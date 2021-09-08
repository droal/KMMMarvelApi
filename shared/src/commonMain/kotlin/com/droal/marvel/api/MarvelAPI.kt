package com.droal.marvel.api

import com.droal.marvel.api.data.character.Character
import com.droal.marvel.api.data.character.CharacterDataWrapper
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

class MarvelAPI {
    private val httpClient = HttpClient{
        install(JsonFeature) {
            val json = Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            }
            serializer = KotlinxSerializer(json)
        }
    }


    companion object {
        private const val ENDPOINT = "https://gateway.marvel.com/v1/public/characters"
        private const val HASH = "0e6dbfbead7be17285a0f8ee368be636"
        private const val TS = "1"
        private const val API_KEY = "a598246bddf59765fc76948d1f952f7b"
    }


    @Throws(Exception::class)
    suspend fun getCharacters(): List<Character> {
        val response: CharacterDataWrapper = httpClient.get(ENDPOINT+"?apikey="+ API_KEY+"&hash="+ HASH+"&ts="+TS)
        return response.data!!.results!!
    }
}