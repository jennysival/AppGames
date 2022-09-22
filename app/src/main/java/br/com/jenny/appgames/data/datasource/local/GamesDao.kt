package br.com.jenny.appgames.data.datasource.local

import androidx.room.*
import br.com.jenny.appgames.data.model.GameResult

@Dao
interface GamesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllGamesDao(gamesList: List<GameResult>)

    @Query("SELECT * FROM games_table")
    fun getAllGamesDao(): List<GameResult>

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateSavedGamesDao(game: GameResult)

    @Query("SELECT * FROM games_table WHERE saved = 1")
    fun getSavedGamesDao(): List<GameResult>
}