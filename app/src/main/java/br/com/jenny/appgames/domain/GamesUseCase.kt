package br.com.jenny.appgames.domain

import android.app.Application
import android.content.res.Resources
import br.com.jenny.appgames.R
import br.com.jenny.appgames.data.datasource.local.GamesDatabase
import br.com.jenny.appgames.data.model.GameResult
import br.com.jenny.appgames.state.ViewState
import kotlin.Exception

class GamesUseCase(application: Application) {
    private val gamesDao = GamesDatabase.getGameDatabase(application).gamesDao()
    private val repository = GamesRepository(gamesDao)

    suspend fun getAllGamesNetwork(page: Int, pageSize: Int): ViewState<List<GameResult>>{
        return try {
            val gamesResponse = repository.getAllGamesNetwork(page = page, pageSize = pageSize)
            ViewState.Success(gamesResponse.gameResults)
        }catch (e: Exception){
            ViewState.Error(Throwable(Resources.getSystem().getString(R.string.txtErrorLoadGames)))
        }
    }

    suspend fun getSavedGamesDao(): ViewState<List<GameResult>>{
        return try {
            val games = repository.getSavedGamesDao()
            ViewState.Success(games)
        }catch (e: Exception){
            ViewState.Error(Exception(Resources.getSystem().getString(R.string.txtErrorLoadSavedGames)))
        }
    }

    suspend fun insertSavedGameDao(game: GameResult): ViewState<GameResult>{
        return try {
            repository.insertSavedGameDao(game)
            ViewState.Success(game)
        }catch (e: Exception){
            ViewState.Error(Exception(Resources.getSystem().getString(R.string.txtErrorSaveGame)))
        }
    }

    suspend fun deleteSavedGameDao(game: GameResult): ViewState<GameResult>{
        return try {
            repository.deleteSavedGameDao(game)
            ViewState.Success(game)
        }catch (e: Exception){
            ViewState.Error(Exception(Resources.getSystem().getString(R.string.txtErrorDeleteGame)))
        }
    }

    suspend fun updateSavedGame(game: GameResult): ViewState<GameResult>{
        return try {
            repository.updateSavedGame(game)
            ViewState.Success(game)
        }catch (e: Exception){
            ViewState.Error(Exception(Resources.getSystem().getString(R.string.txtErrorUpdateGame)))
        }
    }
}