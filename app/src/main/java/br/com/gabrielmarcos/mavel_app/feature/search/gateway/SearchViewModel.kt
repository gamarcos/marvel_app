package br.com.gabrielmarcos.mavel_app.feature.search.gateway

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import br.com.gabrielmarcos.mavel_app.base.business.data.Result
import br.com.gabrielmarcos.mavel_app.base.business.expection.BusinessError
import br.com.gabrielmarcos.mavel_app.base.gateway.Event
import br.com.gabrielmarcos.mavel_app.feature.search.business.data.CharacterData
import br.com.gabrielmarcos.mavel_app.feature.search.business.interactor.SearchUseCase
import java.lang.Exception
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val searchUseCase: SearchUseCase) : ViewModel() {

    val charactersLiveData = MutableLiveData<Result<CharacterData>>()
    var searchLiveData = charactersLiveData.map { Event(it) }

    fun searchByName(name: String) {
        takeIf { name.isNotBlank() }?.run {
            searchUseCase(name.trim(), charactersLiveData)
        } ?: emptyField()
    }

    private fun emptyField() { charactersLiveData.value = Result.Error(BusinessError.INVALID_UNRECOGNIZED_PARAMETER) }
}