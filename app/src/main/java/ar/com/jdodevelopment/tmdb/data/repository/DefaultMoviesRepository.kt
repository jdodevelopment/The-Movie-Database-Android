package ar.com.jdodevelopment.tmdb.data.repository

import ar.com.jdodevelopment.tmdb.data.api.MoviesApi
import ar.com.jdodevelopment.tmdb.data.dto.toEntity
import ar.com.jdodevelopment.tmdb.domain.entity.MovieDetail
import ar.com.jdodevelopment.tmdb.domain.entity.MoviesPage
import ar.com.jdodevelopment.tmdb.domain.error.ErrorResolver
import ar.com.jdodevelopment.tmdb.domain.repository.MoviesRepository
import ar.com.jdodevelopment.tmdb.domain.resource.RemoteResource
import javax.inject.Inject

class DefaultMoviesRepository @Inject constructor(
    private val moviesApi: MoviesApi,
    private val errorResolver: ErrorResolver,
) : MoviesRepository {

    override suspend fun getPopularMovies(page: Long): RemoteResource<MoviesPage> {
        return try {
            val response = moviesApi.getPopularMovies(page)
            RemoteResource.Success(response.toEntity())
        } catch (throwable: Throwable) {
            val errorEntity = errorResolver.resolve(throwable)
            RemoteResource.Failure(errorEntity)
        }
    }

    override suspend fun getMovie(movieId: Long): RemoteResource<MovieDetail> {
        return try {
            val response = moviesApi.getMovie(movieId)
            RemoteResource.Success(response.toEntity())
        } catch (throwable: Throwable) {
            val errorEntity = errorResolver.resolve(throwable)
            RemoteResource.Failure(errorEntity)
        }
    }

}