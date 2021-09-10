package com.droal.marvel.characters.datasource.repository

import com.droal.marvel.characters.datasource.database.IDatabaseSource
import com.droal.marvel.characters.datasource.database.model.toCharacter
import com.droal.marvel.characters.datasource.network.IMarvelAPI
import com.droal.marvel.characters.domain.Character
import com.droal.marvel.characters.util.Response

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