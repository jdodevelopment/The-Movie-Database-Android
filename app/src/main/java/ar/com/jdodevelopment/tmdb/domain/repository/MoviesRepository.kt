package ar.com.jdodevelopment.tmdb.domain.repository

import androidx.paging.PagingData
import ar.com.jdodevelopment.tmdb.domain.entity.Movie
import ar.com.jdodevelopment.tmdb.domain.entity.MovieDetail
import ar.com.jdodevelopment.tmdb.domain.resource.RemoteResource
import kotlinx.coroutines.flow.Flow


interface MoviesRepository {

    fun getPopularMovies(page: Long): Flow<PagingData<Movie>>
    suspend fun getMovie(movieId: Long): RemoteResource<MovieDetail>

}