package br.com.gabrielmarcos.mavel_app.utils.extensions

import br.com.gabrielmarcos.mavel_app.plugin.model.response.CharacterResponse

fun CharacterResponse.getComicsURI(): String {
    return data.results.first().comics.collectionURI
}
