package br.com.jenny.appgames.ui.gameDetail

import android.app.Application
import android.content.res.Resources
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.jenny.appgames.R
import br.com.jenny.appgames.data.model.GameResult
import br.com.jenny.appgames.domain.GamesUseCase
import br.com.jenny.appgames.state.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(application: Application): AndroidViewModel(application) {
    private val usecase = GamesUseCase(application)
    private val _savedGameState = MutableLiveData<ViewState<GameResult>>()
    val savedGameState: LiveData<ViewState<GameResult>> = _savedGameState

    fun updateSavedGame(game: GameResult){
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO){
                    usecase.updateSavedGame(game)
                }
                _savedGameState.value = response
            }catch (e: Exception){
                _savedGameState.value = ViewState.Error(Exception(Resources.getSystem().getString(R.string.txtErrorSaveGame)))
            }
        }
    }
}