package com.droal.marvel.comics.viewmodel

import com.droal.marvel.comics.domain.Comic
import com.droal.marvel.comics.util.ResponseComic


sealed class ComicsListState {
    abstract val response: ResponseComic<List<Comic>>?
}


data class SuccessGetComicListState(override val response: ResponseComic<List<Comic>>) : ComicsListState()
data class LoadingGetComicListState(override val response: ResponseComic<List<Comic>>? = null) : ComicsListState()
data class ErrorGetComicListState(override val response: ResponseComic<List<Comic>>) : ComicsListState()