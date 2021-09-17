package com.droal.marvel.comics.datasource.network.model

import kotlinx.serialization.Serializable


@Serializable
data class ThumbnailComicDto(
    val path: String,
    val extension: String
)