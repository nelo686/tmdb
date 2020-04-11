package es.mrmoustard.tmdb.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SpokenLanguage(
    @PrimaryKey(autoGenerate = false)
    val iso6391: String,
    val name: String
)
