package br.com.gabrielmarcos.mavel_app.plugin.repository

import br.com.gabrielmarcos.mavel_app.feature.search.business.interactor.SearchRepository
import br.com.gabrielmarcos.mavel_app.plugin.model.response.CharacterComicsResponse
import br.com.gabrielmarcos.mavel_app.plugin.model.response.CharacterResponse
import br.com.gabrielmarcos.mavel_app.plugin.retrofit.MarvelService
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val service: MarvelService) :
    SearchRepository {

    override suspend fun getCharacters(name: String): CharacterResponse {
        return service.getCharacters(name = name)
    }

    override suspend fun getComics(url: String): CharacterComicsResponse {
        return service.getComics(url)
    }
}