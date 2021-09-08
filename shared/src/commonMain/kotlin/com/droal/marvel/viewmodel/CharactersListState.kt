package com.droal.marvel.viewmodel

import com.droal.marvel.domain.Character
import com.droal.marvel.util.Response

sealed class CharactersListState {
    abstract val response: Response<List<Character>>?
}

data class SuccessGetCharacterListState(override val response: Response<List<Character>>) : CharactersListState()
data class LoadingGetCharacterListState(override val response: Response<List<Character>>? = null) : CharactersListState()
data class ErrorGetCharacterListState(override val response: Response<List<Character>>) : CharactersListState()