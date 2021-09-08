package com.droal.marvel.domain

data class Character(
    val id: Int,
    val name: String,
    val description: String? = "",
    val modified: String? = "",
    val resourceURI: String?= "",
    val thumbnailPath: String,
    val urls: String ,
    val comics:String ,
    val stories: String,
    val events: String ,
    val series: String,
)