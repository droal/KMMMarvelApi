package com.droal.marvel.characters.datasource.database

import com.droal.marvel.characters.domain.Character
import droal.shareddb.SelectAllCharacters

interface IDatabaseSource {
    suspend fun clearDatabase()
    suspend fun insertCharactersInDB(characters: List<Character>)
    suspend fun getAllCharacters(): List<SelectAllCharacters>
}