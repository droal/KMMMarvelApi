package com.droal.marvel.characters.viewmodel

import com.droal.marvel.characters.domain.Character
import com.droal.marvel.characters.util.Response

sealed class CharactersListState {
    abstract val response: Response<List<Character>>?
}

data class SuccessGetCharacterListState(override val response: Response<List<Character>>) : CharactersListState()
data class LoadingGetCharacterListState(override val response: Response<List<Character>>? = null) : CharactersListState()
data class ErrorGetCharacterListState(override val response: Response<List<Character>>) : CharactersListState()