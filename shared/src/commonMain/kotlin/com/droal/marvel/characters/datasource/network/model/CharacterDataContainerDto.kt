package com.droal.marvel.characters.datasource.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterDataContainerDto(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<CharacterDto>
)