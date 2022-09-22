package br.com.jenny.appgames.ui.savedGamesList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.jenny.appgames.data.model.GameResult
import br.com.jenny.appgames.domain.GamesUseCase
import br.com.jenny.appgames.state.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SavedListViewModel(application: Application): AndroidViewModel(application) {
    private val usecase = GamesUseCase(application)
    private val _savedListState = MutableLiveData<ViewState<List<GameResult>>>()
    val savedListState: LiveData<ViewState<List<GameResult>>> = _savedListState

    fun getSavedGames(){
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO){
                    usecase.getSavedGamesDao()
                }
                _savedListState.value = response
            }catch (e: Exception){
                _savedListState.value = ViewState.Error(Exception("Não foi possível carregar os jogos salvos"))
            }
        }
    }
}