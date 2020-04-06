package es.mrmoustard.tmdb.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import es.mrmoustard.tmdb.data.datasource.agreement.MovieDao
import es.mrmoustard.tmdb.data.entities.db.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        var TEST_MODE = false
        private var INSTANCE: MoviesDatabase? = null

        fun getInstance(context: Context): MoviesDatabase? {
            if (INSTANCE == null) {
                if (TEST_MODE) {
                    synchronized(MoviesDatabase::class) {
                        INSTANCE = Room.inMemoryDatabaseBuilder(
                            context.applicationContext,
                            MoviesDatabase::class.java
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                } else {
                    synchronized(MoviesDatabase::class) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            MoviesDatabase::class.java, "movies.db"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE as MoviesDatabase
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
