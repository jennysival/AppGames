package br.com.jenny.appgames.ui.gamesList

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

class ListViewModel(application: Application): AndroidViewModel(application) {

    private val usecase = GamesUseCase(application)

    private val _gamesListState = MutableLiveData<ViewState<List<GameResult>>>()
    val gamesListState: LiveData<ViewState<List<GameResult>>> = _gamesListState

    val loading = MutableLiveData<ViewState<Boolean>>()

    fun getAllGamesNetwork(page: Int, pageSize: Int){
        loading.value = ViewState.Loading(true)
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO){
                    usecase.getAllGamesNetwork(page = page, pageSize = pageSize)
                }
                _gamesListState.value = response
            }catch (e: Exception){
                _gamesListState.value = ViewState.Error(Throwable("Não foi possível carregar a lista"))
            }finally {
                loading.value = ViewState.Loading(false)
            }
        }
    }

}