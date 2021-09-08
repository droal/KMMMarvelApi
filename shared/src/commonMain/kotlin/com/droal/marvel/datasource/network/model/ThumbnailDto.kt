package com.droal.marvel.datasource.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ThumbnailDto(
    val path: String,
    val extension: String
)