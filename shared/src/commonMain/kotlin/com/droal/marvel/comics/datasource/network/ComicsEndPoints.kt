package com.droal.marvel.comics.datasource.network

object ComicsEndPoints {

    const val BASE_URL = "https://gateway.marvel.com/v1/public/comics"
    const val HASH = "0e6dbfbead7be17285a0f8ee368be636"
    const val TS = "1"
    const val API_KEY = "a598246bddf59765fc76948d1f952f7b"

    const val ALL_COMICS = "$BASE_URL?apikey=$API_KEY&hash=$HASH&ts=$TS"
}