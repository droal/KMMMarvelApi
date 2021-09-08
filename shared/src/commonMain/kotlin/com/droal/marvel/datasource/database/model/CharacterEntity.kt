package com.droal.marvel.datasource.database.model

import com.droal.marvel.domain.Character
import droal.shareddb.SelectAllCharacters

fun SelectAllCharacters.toCharacter(): Character{
    return Character(
        id = id.toInt(),
        name = name,
        description = description,
        modified = modified,
        resourceURI = resourceURI,
        thumbnailPath = path+"."+extension,
        urls = "",
        comics = "",
        stories = "",
        events = "",
        series = ""
    )
}
