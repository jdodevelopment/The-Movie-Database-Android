package ar.com.jdodevelopment.tmdb.presentation.moviedetail

import androidx.annotation.StringRes
import ar.com.jdodevelopment.tmdb.R
import ar.com.jdodevelopment.tmdb.domain.entity.MovieDetail
import ar.com.jdodevelopment.tmdb.domain.error.ResourceException


data class MovieDetailScreenState(
    val loading: Boolean = false,
    val error: MovieDetailErrorState? = null,
    val movieDetail: MovieDetailState? = null,
)

data class MovieDetailState(
    val id: Int,
    val title: String,
    val overview: String,
    val status: String,
    val originalLanguage: String,
    val releaseYear: String?,
    val voteAverage: Double?,
    val posterPath: String,
    val backdropPath: String,
    val genres: String,
)

fun MovieDetail.toMovieDetailState(): MovieDetailState {
    return MovieDetailState(
        id = id,
        title = title,
        overview = overview,
        status = status,
        originalLanguage = originalLanguage.uppercase(),
        releaseYear = if (releaseDate.length >= 4) "(${releaseDate.substring(0, 4)})" else null,
        voteAverage = voteAverage,
        posterPath = posterPath,
        backdropPath = backdropPath,
        genres = genres.joinToString { it.name }
    )
}

data class MovieDetailErrorState(
    @StringRes val message: Int,
)

fun ResourceException.toMovieDetailErrorState(): MovieDetailErrorState {
    return when (this) {
        is ResourceException.Server.NotFound -> MovieDetailErrorState(R.string.error_server_not_found)
        is ResourceException.Server.Unauthorized -> MovieDetailErrorState(R.string.error_server_unauthorized)
        is ResourceException.Server.Unknown -> MovieDetailErrorState(R.string.error_server_unknow)
        is ResourceException.Network -> MovieDetailErrorState(R.string.error_network)
        is ResourceException.Unknown -> MovieDetailErrorState(R.string.error_unknow)
    }
}
