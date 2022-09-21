package br.com.jenny.appgames.domain

import android.app.Application
import br.com.jenny.appgames.data.datasource.local.GamesDatabase
import br.com.jenny.appgames.data.model.GameResult
import br.com.jenny.appgames.state.ViewState
import kotlin.Exception

class GamesUseCase(application: Application) {
    private val gamesDao = GamesDatabase.getCharacterDatabase(application).gamesDao()
    private val repository = GamesRepository(gamesDao)

    suspend fun getAllGamesNetwork(page: Int, pageSize: Int): ViewState<List<GameResult>>{
        return try {
            val games = repository.getAllGamesNetwork(page = page, pageSize = pageSize)
            repository.insertAllGamesDao(games.gameResults)
            getAllGamesDao()
        }catch (e: Exception){
            getAllGamesDao()
        }
    }

    private suspend fun getAllGamesDao(): ViewState<List<GameResult>>{
        return try {
            val games = repository.getAllGamesDao()
            ViewState.Success(games)
        }catch (e: Exception){
            ViewState.Error(Exception("Não foi possível carregar os jogos"))
        }
    }

    suspend fun getSavedGamesDao(): ViewState<List<GameResult>>{
        return try {
            val games = repository.getSavedGamesDao()
            ViewState.Success(games)
        }catch (e: Exception){
            ViewState.Error(Exception("Não foi possível carregar os jogos salvos"))
        }
    }

    suspend fun updateSavedGame(game: GameResult): ViewState<GameResult>{
        return try {
            repository.updateSavedGame(game)
            ViewState.Success(game)
        }catch (e: Exception){
            ViewState.Error(Exception("Não foi possível atualizar o jogo"))
        }
    }
}