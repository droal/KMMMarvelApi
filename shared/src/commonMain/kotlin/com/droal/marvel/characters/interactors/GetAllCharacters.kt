package com.droal.marvel.characters.interactors

import com.droal.marvel.characters.datasource.repository.CharacterRepository
import com.droal.marvel.characters.domain.Character
import com.droal.marvel.characters.util.Response

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