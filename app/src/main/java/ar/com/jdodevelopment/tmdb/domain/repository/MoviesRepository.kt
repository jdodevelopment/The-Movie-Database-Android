package ar.com.jdodevelopment.tmdb.domain.repository

import ar.com.jdodevelopment.tmdb.domain.entity.MovieDetail
import ar.com.jdodevelopment.tmdb.domain.entity.MoviesPage
import ar.com.jdodevelopment.tmdb.domain.resource.RemoteResource


interface MoviesRepository {

    suspend fun getPopularMovies(page: Long): RemoteResource<MoviesPage>
    suspend fun getMovie(movieId: Long): RemoteResource<MovieDetail>

}