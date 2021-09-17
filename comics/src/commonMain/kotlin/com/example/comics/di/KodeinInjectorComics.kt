package com.droal.marvel.di


import com.droal.marvel.comics.datasource.network.IMarvelComicsAPI
import com.droal.marvel.comics.datasource.network.MarvelComicsAPIImpl
import com.droal.marvel.comics.datasource.repository.ComicsRepository
import com.droal.marvel.comics.interactors.GetAllComics
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.coroutines.Dispatchers
import org.kodein.di.*
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
val KodeinInjectorComics = DI{

    bind<CoroutineContext>() with provider { Dispatchers.Main }


    val httpClient = HttpClient{
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            }
            serializer = KotlinxSerializer(json)
        }
    }

    /**
     * NETWORK DATA SOURCE
     */
    bind<IMarvelComicsAPI>() with provider { MarvelComicsAPIImpl(httpClient)}

    /**
     * Disk Data Source
     */


    /**
     * REPOSITORIES
     */
    bind<ComicsRepository>() with provider { ComicsRepository(instance()) }

    /**
     * USECASES
     */
    bind<GetAllComics>() with singleton { GetAllComics(instance()) }
}