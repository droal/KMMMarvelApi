package com.droal.marvel.android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.droal.marvel.android.databinding.FragmentCharactersListBinding
import com.droal.marvel.util.Response
import com.droal.marvel.viewmodel.*

class CharactersListFragment : Fragment() {

    //private val viewmodel: CharactersListViewModel by activityViewModels()
    lateinit var sharedViewModel: CharactersListViewModel

    private lateinit var charactersListObserver: (state: CharactersListState) -> Unit

    private var _binding: FragmentCharactersListBinding? = null
    private val binding get() = _binding!!

    val charactersAdapter = CharactersListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentCharactersListBinding.inflate(inflater, container, false)
        val view = binding.root


        sharedViewModel = ViewModelProviders.of(this).get(CharactersListViewModel::class.java )

        binding.rvCharacters.adapter = charactersAdapter

        binding.button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                //viewmodel.getCharacters(false)
                sharedViewModel.getCharactersList(true)
            }
        })

        /*viewmodel.characters.observe(viewLifecycleOwner, {response ->
            charactersAdapter.data = response
        })*/

        charactersListObserver = {getCharacterListState(sharedViewModel.getCharactersLiveData.value)}
        sharedViewModel.getCharactersLiveData.addObserver(charactersListObserver)

        //viewmodel.getCharacters(false)
        sharedViewModel.getCharactersList(false)

        return view
    }


    fun getCharacterListState(state: CharactersListState) {
        when (state) {
            is SuccessGetCharacterListState -> {
                val response =  state.response as Response.Success
                //onSuccessGetGitHubList(response.data)
                charactersAdapter.data = response.data
            }

            is LoadingGetCharacterListState -> {
            }

            is ErrorGetCharacterListState -> {
                val response =  state.response as Response.Error
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        sharedViewModel.getCharactersLiveData.removeObserver {charactersListObserver }
    }
}