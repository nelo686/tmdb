package es.mrmoustard.tmdb.data.datasource.agreement

import androidx.room.*
import es.mrmoustard.tmdb.domain.entities.MovieFlags

@Dao
interface MovieDao {

    @Query("SELECT * FROM MovieFlags WHERE id = :id")
    suspend fun findById(id: Int): MovieFlags?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(flags: MovieFlags)

    @Delete
    suspend fun delete(flags: MovieFlags)

    @Query("UPDATE MovieFlags SET favourite =:favourite, wannaWatchIt =:wannaWatchIt WHERE id =:movieId")
    suspend fun update(movieId: Int, favourite: Boolean = false, wannaWatchIt: Boolean = false)

    @Query("SELECT * FROM MovieFlags WHERE wannaWatchIt = 1")
    suspend fun findMoviesToWatch(): List<MovieFlags>

    @Query("SELECT * FROM MovieFlags WHERE favourite = 1")
    suspend fun findFavourites(): List<MovieFlags>
}
