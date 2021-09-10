package com.droal.marvel.comics.util

sealed class ResponseComic<out T> {
    class Success<out T>(val data: T) : ResponseComic<T>()
    data class Error(val exception: Throwable,
                     val code: Int? = null,
                     val error: Boolean? = null,
                     val errors: List<ErrorX>? = null,
                     val message: String? = null,
                     val method: String? = null,
                     val path: String? = null) : ResponseComic<Nothing>()
}

data class ErrorX(
    val message: String,
    val path: String
)