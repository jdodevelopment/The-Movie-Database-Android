package ar.com.jdodevelopment.tmdb.domain.entity


data class MoviesPage(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int,
)