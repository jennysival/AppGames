package br.com.jenny.appgames.domain

import br.com.jenny.appgames.data.datasource.network.RetrofitService
import br.com.jenny.appgames.data.model.GamesResponse

class GamesRepository {

    suspend fun getAllGamesNetwork(page: Int, pageSize: Int): GamesResponse {
        return RetrofitService.apiService.getGamesNetwork(page = page, pageSize = pageSize)
    }

}