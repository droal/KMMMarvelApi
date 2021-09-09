package com.droal.marvel.datasource.database

import com.droal.marvel.domain.Character
import droal.shareddb.SelectAllCharacters
import droal.shareddb.cache.Database
import droal.shareddb.cache.DatabaseDriverFactory

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