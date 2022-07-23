package ar.com.jdodevelopment.tmdb.data.constants

object ApiConstants {

    object Path {
        const val GET_POPULAR_MOVIES = "movie/popular"
        const val GET_MOVIE = "movie/{${PathParam.MOVIE_ID}}"
    }

    object QueryParam {
        const val PAGE = "page"
    }

    object PathParam {
        const val MOVIE_ID = "movieId"
    }

}