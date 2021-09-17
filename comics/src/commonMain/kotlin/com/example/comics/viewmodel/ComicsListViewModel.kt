package com.droal.marvel.comics.viewmodel


import com.droal.marvel.comics.domain.Comic
import com.droal.marvel.comics.interactors.GetAllComics
import com.droal.marvel.comics.util.ResponseComic
import com.droal.marvel.di.KodeinInjectorComics
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.kodein.di.instance

@ExperimentalCoroutinesApi
class ComicsListViewModel : ViewModel() {

    var getComicsLiveData =
        MutableLiveData<ComicsListState>(LoadingGetComicListState())

    private val getAllComicsInteractor by KodeinInjectorComics.instance<GetAllComics>()

    fun getComicsList() {
        viewModelScope.launch() {
            getComicsLiveData.postValue(LoadingGetComicListState())

            val response = getAllComicsInteractor.getAllComicsNetwork()
            processComicsListResponseComic(response)
        }
    }

    fun processComicsListResponseComic(response: ResponseComic<List<Comic>>) {
        if (response is ResponseComic.Success) {
            getComicsLiveData.postValue(
                SuccessGetComicListState(
                    response
                )
            )
        } else if (response is ResponseComic.Error) {
            getComicsLiveData.postValue(
                ErrorGetComicListState(
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