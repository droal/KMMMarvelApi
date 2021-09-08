package com.droal.marvel

import com.droal.marvel.api.MarvelAPI
import com.droal.marvel.api.data.character.Character
import droal.shareddb.cache.Database
import droal.shareddb.cache.DatabaseDriverFactory

class MainKMM(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)
    private val marvelApi = MarvelAPI()

    @Throws(Exception::class)
    suspend fun getAllCharacters(updateData: Boolean): List<Character>{
        val charactersDB = database.getAllCharacters()

        return if (charactersDB.isNotEmpty() && !updateData) {
            charactersDB
        } else {
            marvelApi.getCharacters().also {
                database.clearDatabase()
                database.saveCharacters(it)
            }
        }

    }
}