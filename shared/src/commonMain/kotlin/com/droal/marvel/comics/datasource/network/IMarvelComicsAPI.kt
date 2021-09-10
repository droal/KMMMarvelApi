package com.droal.marvel.comics.datasource.network

import com.droal.marvel.comics.domain.Comic
import com.droal.marvel.comics.util.ResponseComic


abstract class IMarvelComicsAPI {
    abstract suspend fun getComics(): ResponseComic<List<Comic>>
}