package com.droal.marvel.comics.datasource.network.model

import com.droal.marvel.comics.domain.Comic
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient


@Serializable
data class ComicDto(
    val id: Int,
    val title: String,
    val description: String?,
    val isbn: String?,
    val thumbnail: ThumbnailComicDto,
    @Transient
    val digitalId: String = "",
    @Transient
    val issueNumber: String = "",
    @Transient
    val variantDescription: String = "",
    @Transient
    val modified: String = "",
    @Transient
    val upc: String = "",
    @Transient
    val diamondCode: String = "",
    @Transient
    val ean: String = "",
    @Transient
    val issn: String = "",
    @Transient
    val format: String = "",
    @Transient
    val pageCount: String = "",
    @Transient
    val textObjects: String = "",
    @Transient
    val resourceURI: String = "",
    @Transient
    val urls: String = "",
    @Transient
    val series: String = "",
    @Transient
    val variants: String = "",
    @Transient
    val collections: String = "",
    @Transient
    val collectedIssues: String = "",
    @Transient
    val dates: String = "",
    @Transient
    val prices: String = "",
    @Transient
    val images: String = "",
    @Transient
    val creators: String = "",
    @Transient
    val characters: String = "",
    @Transient
    val stories: String = "",
    @Transient
    val events: String = "",
)


fun ComicDto.toComic(): Comic {
    return Comic(
        id = id,
        title = title,
        description = description,
        isbn = isbn,
        //Acá se construye el path de la miniatura
        thumbnailPath = thumbnail.path+"."+thumbnail.extension,

    )
}
