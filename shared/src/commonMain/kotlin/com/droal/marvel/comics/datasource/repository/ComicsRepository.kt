package com.droal.marvel.comics.datasource.repository

import com.droal.marvel.comics.datasource.network.IMarvelComicsAPI
import com.droal.marvel.comics.domain.Comic
import com.droal.marvel.comics.util.ResponseComic

class ComicsRepository (
    private val networkDataSource: IMarvelComicsAPI
){
    suspend fun getAllComicsFromNetwork(): ResponseComic<List<Comic>> {
        return networkDataSource.getComics()
    }
}