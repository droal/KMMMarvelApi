package com.droal.marvel.datasource.repository

import com.droal.marvel.datasource.database.IDatabaseSource
import com.droal.marvel.datasource.database.model.toCharacter
import com.droal.marvel.datasource.network.IMarvelAPI
import com.droal.marvel.datasource.network.model.toCharacter
import com.droal.marvel.domain.Character
import com.droal.marvel.util.Response
import droal.shareddb.SelectAllCharacters
import io.ktor.http.cio.*

class CharacterRepository(
    private val dbDataSource: IDatabaseSource,
    private val networkDataSource: IMarvelAPI
) {

    suspend fun getAllCharactersFromDB(): List<Character> {
        return dbDataSource.getAllCharacters().map { it.toCharacter() }
    }

    suspend fun clearDatabase(){
        dbDataSource.clearDatabase()
    }


    suspend fun insertCharactersInDB(characters: List<Character>){
        dbDataSource.insertCharactersInDB(characters)
    }

    suspend fun getAllCharactersFromNetwork(): Response<List<Character>> {
        return networkDataSource.getCharacters()
    }
}