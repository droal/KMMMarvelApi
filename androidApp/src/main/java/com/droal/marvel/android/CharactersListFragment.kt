package com.droal.marvel.android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.droal.marvel.android.databinding.FragmentCharactersListBinding

class CharactersListFragment : Fragment() {

    private val viewmodel: CharactersListViewModel by activityViewModels()

    private var _binding: FragmentCharactersListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentCharactersListBinding.inflate(inflater, container, false)
        val view = binding.root


        val charactersAdapter = CharactersListAdapter()

        binding.rvCharacters.adapter = charactersAdapter

        binding.button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                viewmodel.getCharacters(false)
            }
        })

        viewmodel.getCharacters(false)


        viewmodel.characters.observe(viewLifecycleOwner, {response ->
            charactersAdapter.data = response
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}