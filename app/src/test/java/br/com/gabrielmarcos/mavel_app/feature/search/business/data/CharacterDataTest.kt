package br.com.gabrielmarcos.mavel_app.feature.search.business.data

import br.com.gabrielmarcos.mavel_app.plugin.model.response.CharacterComicsResponse
import br.com.gabrielmarcos.mavel_app.plugin.model.response.CharacterResponse
import br.com.gabrielmarcos.mavel_app.utils.extensions.FileLoaderUtils
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

private const val CHARACTERS_JSON = "samples/characters.json"
private const val CHARACTERS_COMICS_JSON = "samples/characters_comics.json"

@RunWith(MockitoJUnitRunner::class)
class CharacterDataTest {

    private val characterResponse: CharacterResponse
        get() = FileLoaderUtils.getDataFromJsonFile(
            javaClass,
            CharacterResponse::class.java,
            CHARACTERS_JSON
        )!!

    private val characterComicsResponse: CharacterComicsResponse
        get() = FileLoaderUtils.getDataFromJsonFile(
            javaClass,
            CharacterComicsResponse::class.java,
            CHARACTERS_COMICS_JSON
        )!!

    @Test
    fun `when response mapped assert CharacterData response`() {
        val comics = mutableListOf<Pair<String, String>>()
        characterComicsResponse.data.results.map { comics.add(it.title to it.thumbnail.getStandardFantastic()) }
        val expectedResult = CharacterData(
            name = "Avengers",
            description = "Earth's Mightiest Heroes joined forces to take on threats that were" +
                " too big for any one hero to tackle. With a roster that has included Captain America, " +
                "Iron Man, Ant-Man, Hulk, Thor, Wasp and dozens more over the years, the Avengers have come to be regarded as Earth's No. 1 team.",
            imageThumbnail = "http://i.annihil.us/u/prod/marvel/i/mg/9/20/5102c774ebae7/standard_fantastic.jpg",
            comics = comics
        )
        CharacterData.convertingByCharacterResponse(characterResponse, characterComicsResponse)
            .apply {
                assertTrue(name == expectedResult.name)
                assertTrue(description == expectedResult.description)
                assertTrue(imageThumbnail == expectedResult.imageThumbnail)
            }
    }
}