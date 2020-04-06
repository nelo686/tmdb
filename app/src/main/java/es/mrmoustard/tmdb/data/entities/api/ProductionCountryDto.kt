package es.mrmoustard.tmdb.data.entities.api

import es.mrmoustard.tmdb.domain.entities.ProductionCountry
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductionCountryDto(
    @Json(name = "iso_3166_1") val iso31661: String,
    val name: String
)

fun ProductionCountryDto.mapToDomain(): ProductionCountry =
    ProductionCountry(iso31661 = iso31661, name = name)

fun List<ProductionCountryDto>.mapToDomain(): List<ProductionCountry> {
    val list: MutableList<ProductionCountry> = mutableListOf()
    this.forEach { list.add(element = it.mapToDomain()) }
    return list
}
