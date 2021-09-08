package com.droal.marvel.api.data.character

import kotlinx.serialization.Serializable

@Serializable
data class CharacterDataContainer(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Character>
)