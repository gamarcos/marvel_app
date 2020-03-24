package br.com.gabrielmarcos.mavel_app.plugin.retrofit

import br.com.gabrielmarcos.mavel_app.BuildConfig.MARVEL_HASH
import br.com.gabrielmarcos.mavel_app.BuildConfig.MARVEL_PUBLIC_KEY
import br.com.gabrielmarcos.mavel_app.BuildConfig.MARVEL_TS
import br.com.gabrielmarcos.mavel_app.plugin.model.response.CharacterComicsResponse
import br.com.gabrielmarcos.mavel_app.plugin.model.response.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface MarvelService {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("ts") ts: String = MARVEL_TS,
        @Query("apikey") apiKey: String = MARVEL_PUBLIC_KEY,
        @Query("hash") hash: String = MARVEL_HASH,
        @Query("name") name: String
    ): CharacterResponse

    @GET
    suspend fun getComics(
        @Url url: String,
        @Query("ts") ts: String = MARVEL_TS,
        @Query("apikey") apiKey: String = MARVEL_PUBLIC_KEY,
        @Query("hash") hash: String = MARVEL_HASH
    ): CharacterComicsResponse
}
