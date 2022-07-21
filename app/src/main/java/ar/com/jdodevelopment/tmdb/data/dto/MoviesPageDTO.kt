package ar.com.jdodevelopment.tmdb.data.dto

import ar.com.jdodevelopment.tmdb.domain.entity.MoviesPage
import com.google.gson.annotations.SerializedName

data class MoviesPageDTO(
    val page: Int,
    val results: List<MovieDTO>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,
)

fun MoviesPageDTO.toEntity(): MoviesPage {
    return MoviesPage(
        page = page,
        results = results.map { it.toEntity() },
        totalPages = totalPages,
        totalResults = totalResults,
    )
}