package com.droal.marvel.comics.datasource.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ComicDataWrapperDto(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: ComicDataContainerDto? = null,
)