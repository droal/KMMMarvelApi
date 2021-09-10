package com.droal.marvel.characters.datasource.network

import com.droal.marvel.characters.domain.Character
import com.droal.marvel.characters.util.Response

abstract class  IMarvelAPI {
    abstract suspend fun getCharacters(): Response<List<Character>>
}