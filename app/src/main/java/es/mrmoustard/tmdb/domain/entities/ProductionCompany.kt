package es.mrmoustard.tmdb.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductionCompany(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val logoPath: String,
    val originCountry: String
)
