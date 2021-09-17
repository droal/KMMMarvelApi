package com.droal.marvel.comics.datasource.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ComicDataContainerDto(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<ComicDto>
)