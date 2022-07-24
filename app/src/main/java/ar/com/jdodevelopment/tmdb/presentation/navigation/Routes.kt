package ar.com.jdodevelopment.tmdb.presentation.navigation


const val MOVIE_ID_PARAM = "movieId"
const val POPULAR_MOVIES_ROUTE = "popular_movies"
const val MOVIE_DETAIL_ROUTE = "movie_detail"

sealed class Routes(val value: String) {
    object PopularMovies : Routes(POPULAR_MOVIES_ROUTE)
    object MovieDetail : Routes("$MOVIE_DETAIL_ROUTE/{$MOVIE_ID_PARAM}") {
        fun withParams(movieId: Int) = "$MOVIE_DETAIL_ROUTE/$movieId"
    }
}