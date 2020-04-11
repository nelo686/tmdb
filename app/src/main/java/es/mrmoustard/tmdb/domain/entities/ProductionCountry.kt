package es.mrmoustard.tmdb.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductionCountry(
    @PrimaryKey(autoGenerate = false)
    val iso31661: String,
    val name: String
)
