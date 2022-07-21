package ar.com.jdodevelopment.tmdb.data.dto

import com.google.gson.annotations.SerializedName

data class MoviesPageDTO(
    val page: Int,
    val results: List<MovieDTO>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,
)