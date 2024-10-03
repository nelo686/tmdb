package es.mrmoustard.tmdb.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Collection(
    @PrimaryKey(autoGenerate = false)
    val id: Int = -1,
    val name: String = "",
    val posterPath: String = "",
    val backdropPath: String = ""
)
