package com.droal.marvel.comics.interactors

import com.droal.marvel.comics.datasource.repository.ComicsRepository
import com.droal.marvel.comics.domain.Comic
import com.droal.marvel.comics.util.ResponseComic

class GetAllComics(
    private val repository: ComicsRepository
) {

    @Throws(Exception::class)
    suspend fun getAllComicsNetwork(): ResponseComic<List<Comic>> {
        val response: ResponseComic<List<Comic>>  = repository.getAllComicsFromNetwork()
        return response
    }
}