package br.com.jenny.appgames.domain

import android.app.Application
import br.com.jenny.appgames.data.model.GameResult
import br.com.jenny.appgames.state.ViewState
import java.lang.Exception

class GamesUseCase(application: Application) {
    private val repository = GamesRepository()

    suspend fun getAllGamesNetwork(page: Int, pageSize: Int): ViewState<List<GameResult>>{
        return try {
            val games = repository.getAllGamesNetwork(page = page, pageSize = pageSize)
            ViewState.Success(games.gameResults)
        }catch (e: Exception){
            ViewState.Error(Throwable("Não foi possível carregar a lista de jogos"))
        }
    }
}