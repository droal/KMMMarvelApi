package com.droal.marvel.datasource.database

import com.droal.marvel.domain.Character
import droal.shareddb.SelectAllCharacters

interface IDatabaseSource {
    suspend fun clearDatabase()
    suspend fun insertCharactersInDB(characters: List<Character>)
    suspend fun getAllCharacters(): List<SelectAllCharacters>
}