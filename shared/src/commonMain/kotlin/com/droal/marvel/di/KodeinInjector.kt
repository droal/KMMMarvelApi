package com.droal.marvel.di

import com.droal.marvel.characters.datasource.database.DatabaseSourceImpl
import com.droal.marvel.characters.datasource.database.IDatabaseSource
import com.droal.marvel.characters.datasource.network.IMarvelAPI
import com.droal.marvel.characters.datasource.network.MarvelAPIImpl
import com.droal.marvel.characters.datasource.repository.CharacterRepository
import com.droal.marvel.characters.interactors.GetAllCharacters
import droal.shareddb.cache.DatabaseCreator
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.coroutines.Dispatchers
import org.kodein.di.*
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
val KodeinInjector = DI{

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
    bind<IMarvelAPI>() with provider { MarvelAPIImpl(httpClient)}

    /**
     * Disk Data Source
     */
    bind<IDatabaseSource>() with provider { DatabaseSourceImpl(DatabaseCreator.getDataBase(InjectorCommon.context)) }


    /**
     * REPOSITORIES
     */
    bind<CharacterRepository>() with provider { CharacterRepository(instance(), instance()) }

    /**
     * USECASES
     */
    bind<GetAllCharacters>() with singleton { GetAllCharacters(instance()) }
}