package br.com.gabrielmarcos.mavel_app.feature.search.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import br.com.gabrielmarcos.mavel_app.R
import br.com.gabrielmarcos.mavel_app.base.business.data.Result
import br.com.gabrielmarcos.mavel_app.base.view.BaseFragment
import br.com.gabrielmarcos.mavel_app.feature.search.gateway.SearchViewModel
import br.com.gabrielmarcos.mavel_app.utils.extensions.hide
import br.com.gabrielmarcos.mavel_app.utils.extensions.hideKeyboard
import br.com.gabrielmarcos.mavel_app.utils.extensions.navigate
import br.com.gabrielmarcos.mavel_app.utils.extensions.onBackPressed
import br.com.gabrielmarcos.mavel_app.utils.extensions.show
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : BaseFragment() {

    private val viewModel: SearchViewModel by activityViewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        setUpViews()
        onBackPressed { activity?.finish() }
    }

    private fun setUpViewModel() {
        viewModel.searchLiveData.observe(viewLifecycleOwner, Observer {
            if (it.getContentIfNotHandled() != null) {
                when (it.peekContent()) {
                    is Result.Loading -> loadingSearch.show()
                    is Result.Success -> navigateToCharacter()
                    is Result.Error -> showError(it.peekContent() as Result.Error)
                }
            }
        })
    }

    private fun navigateToCharacter() {
        loadingSearch.hide()
        navigate(R.id.action_to_character)
    }

    private fun showError(error: Result.Error) {
        loadingSearch.hide()
        buttonSearch.isEnabled = true
        showError(error.exception.message)
    }

    private fun setUpViews() {
        loadingSearch.hide()
        textSearch.addTextChangedListener(SearchListener())
        buttonSearch.setOnClickListener {
            searchByName()
        }
        textSearch.doOnTextChanged { text, _, _, _ ->
            buttonSearch.isEnabled = !text.isNullOrEmpty()
        }

        textSearch.setOnEditorActionListener { _, _, _ ->
            searchByName()
            true
        }
    }

    private fun searchByName() {
        buttonSearch.isEnabled = false
        activity.hideKeyboard()
        viewModel.searchByName(textSearch.text.toString())
    }

    override val idToShowInfoBar: View?
        get() = containerSearch

    inner class SearchListener() : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            buttonSearch.isEnabled = s.isNullOrBlank()
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }
}