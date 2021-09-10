package com.droal.marvel.characters.datasource.database

import com.droal.marvel.characters.domain.Character
import droal.shareddb.SelectAllCharacters
import droal.shareddb.cache.Database

class DatabaseSourceImpl(private val database: Database?): IDatabaseSource {

    override suspend fun clearDatabase() {
        database?.clearDatabase()
    }

    override suspend fun insertCharactersInDB(characters: List<Character>) {
        database?.insertCharacters(characters)
    }

    override suspend fun getAllCharacters(): List<SelectAllCharacters> {
        return database?.getAllCharacters() ?: emptyList()
    }
}