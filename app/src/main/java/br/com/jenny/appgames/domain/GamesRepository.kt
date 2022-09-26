package br.com.jenny.appgames.domain

import br.com.jenny.appgames.data.datasource.local.GamesDao
import br.com.jenny.appgames.data.datasource.network.RetrofitService
import br.com.jenny.appgames.data.model.GameResult
import br.com.jenny.appgames.data.model.GamesResponse

class GamesRepository(private val gamesDao: GamesDao) {

    suspend fun getAllGamesNetwork(page: Int, pageSize: Int): GamesResponse {
        return RetrofitService.apiService.getGamesNetwork(page = page, pageSize = pageSize)
    }

    suspend fun insertSavedGameDao(game: GameResult){
        gamesDao.insertSavedGameDao(game)
    }

    suspend fun deleteSavedGameDao(game: GameResult){
        gamesDao.deleteSavedGame(game)
    }

    suspend fun getSavedGamesDao(): List<GameResult> = gamesDao.getSavedGamesDao()

    suspend fun updateSavedGame(game: GameResult){
        gamesDao.updateSavedGamesDao(game)
    }

}