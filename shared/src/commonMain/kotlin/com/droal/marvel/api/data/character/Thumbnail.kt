package com.droal.marvel.api.data.character

import kotlinx.serialization.Serializable

@Serializable
data class Thumbnail(
    val path: String,
    val extension: String
)