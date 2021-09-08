package com.droal.marvel.api.data.character

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Character(
    val id: Int,
    val name: String,
    val description: String?,
    val modified: String?,
    val resourceURI: String?,
    val thumbnail: Thumbnail,
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