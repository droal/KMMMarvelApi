package com.droal.marvel.characters.datasource.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ThumbnailDto(
    val path: String,
    val extension: String
)