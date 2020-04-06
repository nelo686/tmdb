package es.mrmoustard.tmdb.data.datasource.agreement

import androidx.room.*
import es.mrmoustard.tmdb.data.entities.db.Movie

@Dao
interface MovieDao {

    fun movieCount(): Int

    @Query("SELECT * FROM Movies WHERE id = :id")
    fun findById(id: Int): Movie

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies: List<Movie>)

    @Delete
    fun delete(movie: Movie)

    @Query("UPDATE Movies SET favourite =:favourite, wannaWatchIt =:wannaWatchIt WHERE id =:movieId")
    fun update(movieId: Int, favourite: Boolean = false, wannaWatchIt: Boolean = false)
}
