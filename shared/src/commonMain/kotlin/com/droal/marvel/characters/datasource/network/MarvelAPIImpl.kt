package com.droal.marvel.characters.datasource.network

import com.droal.marvel.characters.datasource.network.model.CharacterDataWrapperDto
import com.droal.marvel.characters.datasource.network.model.toCharacter
import com.droal.marvel.characters.domain.Character
import com.droal.marvel.characters.util.Response
import io.ktor.client.*
import io.ktor.client.request.*

class MarvelAPIImpl(
    private val httpClient: HttpClient
): IMarvelAPI() {


    override suspend fun getCharacters(): Response<List<Character>> {
        val response: CharacterDataWrapperDto = httpClient.get(EndPoints.ALL_CHARACTERS)
        val container = response.data
        val characters = container?.results
        return if (characters != null) {
            Response.Success(characters.map{it.toCharacter()})
        }else{
            Response.Success(emptyList())
        }
    }
}