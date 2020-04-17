package es.mrmoustard.tmdb.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieFlags(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String = "",
    val backdropPath: String = "",
    val favourite: Boolean = false,
    val wannaWatchIt: Boolean = false
)
