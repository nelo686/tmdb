package es.mrmoustard.tmdb.data.entities.api

import es.mrmoustard.tmdb.domain.entities.ProductionCompany
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductionCompanyDto(
    val id: Int,
    val name: String,
    @Json(name = "logo_path") val logoPath: String?,
    @Json(name = "origin_country") val originCountry: String
)

fun ProductionCompanyDto.mapToDomain(): ProductionCompany =
    ProductionCompany(
        id = id,
        name = name,
        logoPath = logoPath ?: "",
        originCountry = originCountry
    )

fun List<ProductionCompanyDto>.mapToDomain(): List<ProductionCompany> {
    val list: MutableList<ProductionCompany> = mutableListOf()
    this.forEach { list.add(element = it.mapToDomain()) }
    return list
}
