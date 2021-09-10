package com.droal.marvel.characters.datasource.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterDataWrapperDto(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: CharacterDataContainerDto? = null,
)