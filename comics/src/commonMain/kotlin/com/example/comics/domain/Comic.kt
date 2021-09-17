package com.droal.marvel.comics.domain


data class Comic(
    val id: Int,
    val title: String,
    val description: String? = "",
    val isbn: String? = "",
    val thumbnailPath: String,
)
