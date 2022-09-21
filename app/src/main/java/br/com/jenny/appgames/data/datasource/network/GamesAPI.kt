package br.com.jenny.appgames.data.datasource.network

import br.com.jenny.appgames.data.datasource.network.RetrofitService.Companion.API_KEY
import br.com.jenny.appgames.data.model.GamesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesAPI {

    @GET("games")
    suspend fun getGamesNetwork(
        @Query("key") apiKey: String = API_KEY,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): GamesResponse

}