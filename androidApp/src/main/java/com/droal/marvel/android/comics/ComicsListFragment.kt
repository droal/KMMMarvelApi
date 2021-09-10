package com.droal.marvel.android.comics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.droal.marvel.android.databinding.FragmentComicsListBinding
import com.droal.marvel.comics.util.ResponseComic
import com.droal.marvel.comics.viewmodel.*
import com.droal.marvel.comics.viewmodel.ComicsListState
import com.droal.marvel.comics.viewmodel.ComicsListViewModel

class ComicsListFragment : Fragment() {
    lateinit var sharedViewModel: ComicsListViewModel

    private lateinit var comicsListObserver: (state: ComicsListState) -> Unit

    private var _binding: FragmentComicsListBinding? = null
    private val binding get() = _binding!!

    val comicsAdapter = ComicsListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentComicsListBinding.inflate(inflater, container, false)
        val view = binding.root


        sharedViewModel = ViewModelProviders.of(this).get(ComicsListViewModel::class.java )

        binding.rvComics.adapter = comicsAdapter


        comicsListObserver = {getComicsListState(sharedViewModel.getComicsLiveData.value)}
        sharedViewModel.getComicsLiveData.addObserver(comicsListObserver)

        sharedViewModel.getComicsList()

        return view
    }


    fun getComicsListState(state: ComicsListState) {
        when (state) {
            is SuccessGetComicListState -> {
                val response =  state.response as ResponseComic.Success
                comicsAdapter.data = response.data
            }

            is LoadingGetComicListState -> {
            }

            is ErrorGetComicListState -> {
                val response =  state.response as ResponseComic.Error
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        sharedViewModel.getComicsLiveData.removeObserver {comicsListObserver }
    }
}