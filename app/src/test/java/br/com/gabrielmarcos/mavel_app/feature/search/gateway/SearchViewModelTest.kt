package br.com.gabrielmarcos.mavel_app.feature.search.gateway

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.gabrielmarcos.mavel_app.base.business.data.Result
import br.com.gabrielmarcos.mavel_app.base.business.expection.BusinessError
import br.com.gabrielmarcos.mavel_app.feature.search.business.expection.NoResultException
import br.com.gabrielmarcos.mavel_app.feature.search.business.interactor.SearchRepository
import br.com.gabrielmarcos.mavel_app.feature.search.business.interactor.SearchUseCase
import br.com.gabrielmarcos.mavel_app.plugin.model.response.CharacterComicsResponse
import br.com.gabrielmarcos.mavel_app.plugin.model.response.CharacterResponse
import br.com.gabrielmarcos.mavel_app.utils.extensions.FileLoaderUtils
import br.com.gabrielmarcos.mavel_app.utils.extensions.getComicsURI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

private const val CHARACTERS_JSON = "samples/characters.json"
private const val CHARACTERS_COMICS_JSON = "samples/characters_comics.json"

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    private val characterResponse: CharacterResponse?
        get() = FileLoaderUtils.getDataFromJsonFile(
            javaClass,
            CharacterResponse::class.java,
            CHARACTERS_JSON
        )

    private val characterComicsResponse: CharacterComicsResponse?
        get() = FileLoaderUtils.getDataFromJsonFile(
            javaClass,
            CharacterComicsResponse::class.java,
            CHARACTERS_COMICS_JSON
        )

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: SearchViewModel

    lateinit var useCase: SearchUseCase

    @Mock
    lateinit var mockRepository: SearchRepository

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        useCase = SearchUseCase(mockRepository)
        viewModel = SearchViewModel(useCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when execute use case then return success result`() {
        runBlockingTest {
            val name = "Deadpool"

            val url = characterResponse?.getComicsURI().orEmpty()

            `when`(mockRepository.getCharacters(name)).thenReturn(characterResponse)
            `when`(mockRepository.getComics(url)).thenReturn(characterComicsResponse)

            viewModel.searchByName(name)

            viewModel.charactersLiveData.observeForever {
                if (it is Result.Success) {
                    val (title, description, image, comics) = it.data
                    assertEquals("Avengers", title)
                    assertTrue(description.startsWith("Earth's Mightiest Heroes joined forces to take "))
                    assertTrue(image.startsWith("http://i.annihil.us/u/prod/marvel/i/mg/9/20/5102c774ebae7/"))
                    assertEquals(20, comics.size)
                }
            }
        }
    }

    @Test
    fun `when execute use case not found character assert return not_found error`() {
        runBlockingTest {
            val wrongName = "Deadpo"

            val url = characterResponse?.getComicsURI().orEmpty()

            `when`(mockRepository.getCharacters(wrongName)).thenThrow(NoResultException())
            `when`(mockRepository.getComics(url)).thenReturn(characterComicsResponse)

            viewModel.searchByName(wrongName)

            viewModel.charactersLiveData.observeForever {
                if (it is Result.Error) {
                    assertTrue(it.exception == BusinessError.NOT_FOUND)
                }
            }
        }
    }

    @Test
    fun `when send a empty comics then return error`() {
        viewModel.searchByName("")
        assertTrue(
            viewModel.charactersLiveData.value ==
                Result.Error(BusinessError.INVALID_UNRECOGNIZED_PARAMETER)
        )
    }
}
