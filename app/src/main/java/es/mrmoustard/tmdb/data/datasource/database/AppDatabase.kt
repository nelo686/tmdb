package es.mrmoustard.tmdb.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import es.mrmoustard.tmdb.data.datasource.database.entities.MovieStatus

@Database(entities = [MovieStatus::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dataBaseDao(): MovieDao
}