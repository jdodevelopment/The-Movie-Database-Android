package ar.com.jdodevelopment.tmdb.data.api


import ar.com.jdodevelopment.tmdb.data.constants.ApiConstants
import ar.com.jdodevelopment.tmdb.data.constants.ApiConstants.PathParam
import ar.com.jdodevelopment.tmdb.data.constants.ApiConstants.QueryParam
import ar.com.jdodevelopment.tmdb.data.dto.MovieDetailDTO
import ar.com.jdodevelopment.tmdb.data.dto.MoviesPageDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET(ApiConstants.Path.GET_POPULAR_MOVIES)
    suspend fun getPopularMovies(@Query(QueryParam.PAGE) page: Long): MoviesPageDTO

    @GET(ApiConstants.Path.GET_MOVIE)
    suspend fun getMovie(@Path(PathParam.MOVIE_ID) movieId: Long): MovieDetailDTO

}