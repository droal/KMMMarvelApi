package com.droal.marvel.characters.datasource.network.model

import com.droal.marvel.characters.domain.Character
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class CharacterDto(
    val id: Int,
    val name: String,
    val description: String?,
    val modified: String?,
    val resourceURI: String?,
    val thumbnail: ThumbnailDto,
    @Transient
    val urls: String = "",
    @Transient
    val comics:String = "",
    @Transient
    val stories: String = "",
    @Transient
    val events: String = "",
    @Transient
    val series: String = "",
)

fun CharacterDto.toCharacter(): Character{
    return Character(
        id = id,
        name = name,
        description = description,
        modified = modified,
        resourceURI = resourceURI,
        //Acá se construye el path de la miniatura
        thumbnailPath = thumbnail.path+"."+thumbnail.extension,
        urls = urls,
        comics = comics,
        stories = stories,
        events = events,
        series = series
    )
}
