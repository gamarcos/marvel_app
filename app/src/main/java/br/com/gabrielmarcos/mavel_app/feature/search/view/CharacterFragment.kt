package br.com.gabrielmarcos.mavel_app.feature.search.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import br.com.gabrielmarcos.mavel_app.R
import br.com.gabrielmarcos.mavel_app.base.business.data.Result
import br.com.gabrielmarcos.mavel_app.base.view.BaseFragment
import br.com.gabrielmarcos.mavel_app.feature.search.business.data.CharacterData
import br.com.gabrielmarcos.mavel_app.feature.search.gateway.SearchViewModel
import br.com.gabrielmarcos.mavel_app.utils.extensions.loadImage
import br.com.gabrielmarcos.mavel_app.utils.extensions.onBackPressed
import kotlinx.android.synthetic.main.fragment_character.*

class CharacterFragment : BaseFragment() {

    private val viewModel: SearchViewModel by activityViewModels { viewModelFactory }
    private val adapter: ComicsAdapter = ComicsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpList()
        setUpViewModel()
        setUpBackPressed()
    }

    private fun setUpViewModel() {
        viewModel.charactersLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Success -> bind(it.data)
                is Result.Error, Result.Loading -> onBackPressed()
            }
        })
    }

    private fun bind(data: CharacterData) {
        with(data) {
            nameCharacter.text = name
            descriptionCharacter.text = description
            imageCharacter.loadImage(imageThumbnail)
            adapter.submitList(data.comics)
        }
    }

    private fun setUpList() {
        listComics.adapter = adapter
    }

    private fun setUpBackPressed() {
        backCharacter.setOnClickListener { requireActivity().onBackPressed() }
    }
}