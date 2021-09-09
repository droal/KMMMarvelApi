package com.droal.marvel.interactors

import com.droal.marvel.datasource.database.IDatabaseSource
import com.droal.marvel.datasource.network.IMarvelAPI
import com.droal.marvel.datasource.repository.CharacterRepository
import com.droal.marvel.domain.Character
import com.droal.marvel.util.Response

class GetAllCharacters(
    private val repository: CharacterRepository
) {

    @Throws(Exception::class)
    suspend fun getAllCharacters(updateData: Boolean): Response<List<Character>> {
        val charactersDB = getAllCharactersDB()

        return if (charactersDB.isNotEmpty() && !updateData) {
            Response.Success(charactersDB)
        } else {
            getAllCharactersNetwork()
        }
    }

    @Throws(Exception::class)
    suspend fun getAllCharactersNetwork(): Response<List<Character>>{
        val response: Response<List<Character>> = repository.getAllCharactersFromNetwork()
        repository.clearDatabase()

        if (response is Response.Success){
            repository.insertCharactersInDB(response.data)
        }
        return response
    }

    @Throws(Exception::class)
    suspend fun getAllCharactersDB(): List<Character> {
        val charactersDB = repository.getAllCharactersFromDB()
        return charactersDB
    }

    /*@Throws(Exception::class)
    suspend fun getAllCharacters(updateData: Boolean): List<Character>{
        val charactersDB = repository.getAllCharactersFromDB()

        return if (charactersDB.isNotEmpty() && !updateData) {
            charactersDB
        } else {
            repository.getAllCharactersFromNetwork().also {
                repository.clearDatabase()
                repository.insertCharactersInDB(it)
            }
        }

    }*/

}