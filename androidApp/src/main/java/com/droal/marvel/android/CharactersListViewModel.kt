package com.droal.marvel.android

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droal.marvel.MainKMM
import com.droal.marvel.api.MarvelAPI
import com.droal.marvel.api.data.character.Character
import com.droal.marvel.api.data.character.CharacterDataContainer
import droal.shareddb.cache.DatabaseDriverFactory
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class CharactersListViewModel(application: Application): AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    private val mainApi = MainKMM(DatabaseDriverFactory(context))
    private val mainScope = MainScope()


    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>>
        get() = _characters

    private val _responseMsg = MutableLiveData<String>()
    val responseMsg: LiveData<String>
        get() = _responseMsg


    public fun getCharacters(updateData: Boolean){
        mainScope.launch {
            kotlin.runCatching {
                mainApi.getAllCharacters(updateData)
            }.onSuccess {
                _characters.value = it
            }.onFailure {
                _responseMsg.value = "Error: ${it.localizedMessage}"
            }
        }
    }
}