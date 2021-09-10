package com.droal.marvel.comics.datasource.network


import com.droal.marvel.comics.datasource.network.model.ComicDataWrapperDto
import com.droal.marvel.comics.datasource.network.model.toComic
import com.droal.marvel.comics.domain.Comic
import com.droal.marvel.comics.util.ResponseComic
import io.ktor.client.*
import io.ktor.client.request.*


class MarvelComicsAPIImpl(
    private val httpClient: HttpClient
): IMarvelComicsAPI(){


    override suspend fun getComics(): ResponseComic<List<Comic>> {
        val response: ComicDataWrapperDto = httpClient.get(ComicsEndPoints.ALL_COMICS)
        val container = response.data
        val comics = container?.results
        return if (comics != null) {
            ResponseComic.Success(comics.map{it.toComic()})
        }else{
            ResponseComic.Success(emptyList())
        }
    }
}