package es.mrmoustard.tmdb.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Genre(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String
)
