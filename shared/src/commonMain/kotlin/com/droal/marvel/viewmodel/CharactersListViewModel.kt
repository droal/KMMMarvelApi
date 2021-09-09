package com.droal.marvel.viewmodel

import com.droal.marvel.di.KodeinInjector
import com.droal.marvel.domain.Character
import com.droal.marvel.interactors.GetAllCharacters
import com.droal.marvel.util.Response
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.kodein.di.instance

@ExperimentalCoroutinesApi
class CharactersListViewModel : ViewModel() {

    /*private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>>
        get() = _characters*/
    var getCharactersLiveData =
        MutableLiveData<CharactersListState>(LoadingGetCharacterListState())


    // USE CASE
    private val getAllCharactersInteractor by KodeinInjector.instance<GetAllCharacters>()


    fun getCharactersList(updateData: Boolean) {
        viewModelScope.launch() {
            getCharactersLiveData.postValue(LoadingGetCharacterListState())

            val response = getAllCharactersInteractor.getAllCharacters(updateData)
            processCharactersListResponse(response)
        }
    }

    fun processCharactersListResponse(response: Response<List<Character>>) {
        if (response is Response.Success) {
            getCharactersLiveData.postValue(
                SuccessGetCharacterListState(
                    response
                )
            )
        } else if (response is Response.Error) {
            getCharactersLiveData.postValue(
                ErrorGetCharacterListState(
                    response
                )
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}