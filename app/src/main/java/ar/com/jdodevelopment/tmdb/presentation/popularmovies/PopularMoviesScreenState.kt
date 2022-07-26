package ar.com.jdodevelopment.tmdb.presentation.popularmovies

import androidx.annotation.StringRes
import ar.com.jdodevelopment.tmdb.R
import ar.com.jdodevelopment.tmdb.domain.error.ResourceException


data class PopularMoviesScreenState(
    val error: PopularMoviesErrorState? = null,
)

data class PopularMoviesErrorState(
    @StringRes val message: Int,
)

fun ResourceException.toPopularMoviesErrorState() : PopularMoviesErrorState {
    return when(this) {
        is ResourceException.Server.NotFound -> PopularMoviesErrorState(R.string.error_server_not_found)
        is ResourceException.Server.Unauthorized -> PopularMoviesErrorState(R.string.error_server_unauthorized)
        is ResourceException.Server.Unknown -> PopularMoviesErrorState(R.string.error_server_unknow)
        is ResourceException.Network -> PopularMoviesErrorState(R.string.error_network)
        is ResourceException.Unknown -> PopularMoviesErrorState(R.string.error_unknow)
    }
}
