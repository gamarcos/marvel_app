package br.com.gabrielmarcos.mavel_app.feature.search.business.interactor

import br.com.gabrielmarcos.mavel_app.plugin.model.response.CharacterComicsResponse
import br.com.gabrielmarcos.mavel_app.plugin.model.response.CharacterResponse

interface SearchRepository {
    suspend fun getCharacters(name: String): CharacterResponse
    suspend fun getComics(url: String): CharacterComicsResponse
}