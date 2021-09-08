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
    suspend fun getAllCharacters(updateData: Boolean): Response<List<Character>>{
        val list: Response<List<Character>> = repository.getAllCharactersFromNetwork()
        //val s: Response.Success<List<Character>> = Response.Success(list)

        return list

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