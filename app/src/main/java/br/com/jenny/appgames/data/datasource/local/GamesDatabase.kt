package br.com.jenny.appgames.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.jenny.appgames.data.model.GameResult

@Database(entities = [GameResult::class], version = 1)
abstract class GamesDatabase: RoomDatabase() {
    abstract fun gamesDao(): GamesDao

    companion object {
        @Volatile
        private var INSTANCE: GamesDatabase? = null

        fun getGameDatabase(context: Context): GamesDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GamesDatabase::class.java,
                    "games_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}