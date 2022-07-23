package ar.com.jdodevelopment.tmdb.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import ar.com.jdodevelopment.tmdb.data.api.MoviesApi
import ar.com.jdodevelopment.tmdb.data.constants.ApiConstants
import ar.com.jdodevelopment.tmdb.data.dto.toEntity
import ar.com.jdodevelopment.tmdb.domain.entity.Movie
import ar.com.jdodevelopment.tmdb.domain.entity.MovieDetail
import ar.com.jdodevelopment.tmdb.domain.error.ErrorResolver
import ar.com.jdodevelopment.tmdb.domain.repository.MoviesRepository
import ar.com.jdodevelopment.tmdb.domain.resource.RemoteResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultMoviesRepository @Inject constructor(
    private val moviesPagingSource: PagingSource<Int, Movie>,
    private val moviesApi: MoviesApi,
    private val errorResolver: ErrorResolver,
) : MoviesRepository {

    override fun getPopularMovies(page: Long): Flow<PagingData<Movie>> {
        return Pager(PagingConfig(pageSize = ApiConstants.PAGE_SIZE)) {
            moviesPagingSource
        }.flow
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