package br.com.gabrielmarcos.mavel_app.feature.search.business.interactor

import br.com.gabrielmarcos.mavel_app.base.business.interactor.UseCase
import br.com.gabrielmarcos.mavel_app.feature.search.business.data.CharacterData
import br.com.gabrielmarcos.mavel_app.feature.search.business.data.CharacterData.Companion.convertingByCharacterResponse
import br.com.gabrielmarcos.mavel_app.feature.search.business.expection.NoResultException
import br.com.gabrielmarcos.mavel_app.utils.extensions.getComicsURI
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: SearchRepository) :
    UseCase<String, CharacterData>() {

    override suspend fun execute(param: String?): CharacterData {
        val character = repository.getCharacters(param!!)
        val url = character.getComicsURI()
        val comics = repository.getComics(url)

        if (character.data.count == 0) throw NoResultException()

        return convertingByCharacterResponse(character, comics)
    }
}
