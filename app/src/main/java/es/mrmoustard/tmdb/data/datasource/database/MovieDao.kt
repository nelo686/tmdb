package es.mrmoustard.tmdb.data.datasource.database

import androidx.room.*
import es.mrmoustard.tmdb.data.datasource.database.entities.MovieStatus

@Dao
interface MovieDao {

    @Query("SELECT * FROM MovieStatus WHERE id = :id")
    suspend fun findById(id: Int): MovieStatus?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: MovieStatus)

    @Delete
    suspend fun delete(item: MovieStatus)

    @Query("UPDATE MovieStatus SET favourite =:favourite, wannaWatchIt =:wannaWatchIt WHERE id =:movieId")
    suspend fun update(movieId: Int, favourite: Boolean = false, wannaWatchIt: Boolean = false)

    @Query("SELECT * FROM MovieStatus WHERE wannaWatchIt = 1")
    suspend fun findMoviesToWatch(): List<MovieStatus>

    @Query("SELECT * FROM MovieStatus WHERE favourite = 1")
    suspend fun findFavourites(): List<MovieStatus>
}
