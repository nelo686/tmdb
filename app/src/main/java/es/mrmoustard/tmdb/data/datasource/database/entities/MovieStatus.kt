package es.mrmoustard.tmdb.data.datasource.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import es.mrmoustard.tmdb.domain.entities.Movie

@Entity
data class MovieStatus(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String = "",
    val backdropPath: String = "",
    val favourite: Boolean = false,
    val wannaWatchIt: Boolean = false
)

fun MovieStatus.mapToDomain(): Movie =
    Movie(
        id = id,
        title = title,
        backdropPath = backdropPath,
        favourite = favourite,
        wannaWatchIt = wannaWatchIt
    )
