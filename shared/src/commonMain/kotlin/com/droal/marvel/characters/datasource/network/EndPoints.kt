package com.droal.marvel.characters.datasource.network

object EndPoints {
    const val BASE_URL = "https://gateway.marvel.com/v1/public/characters"
    const val HASH = "0e6dbfbead7be17285a0f8ee368be636"
    const val TS = "1"
    const val API_KEY = "a598246bddf59765fc76948d1f952f7b"

    const val ALL_CHARACTERS = "$BASE_URL?apikey=$API_KEY&hash=$HASH&ts=$TS"

}