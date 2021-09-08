package com.droal.marvel.api.data.character

import kotlinx.serialization.Serializable

@Serializable
data class CharacterDataWrapper(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: CharacterDataContainer? = null,
)