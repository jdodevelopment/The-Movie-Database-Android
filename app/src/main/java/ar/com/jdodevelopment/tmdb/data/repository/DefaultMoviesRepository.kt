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
import ar.com.jdodevelopment.tmdb.domain.error.ExceptionResolver
import ar.com.jdodevelopment.tmdb.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultMoviesRepository @Inject constructor(
    private val moviesPagingSource: PagingSource<Int, Movie>,
    private val moviesApi: MoviesApi,
    private val exceptionResolver: ExceptionResolver,
) : MoviesRepository {

    override fun getPopularMovies(): Flow<PagingData<Movie>> {
        return Pager(
            PagingConfig(
                pageSize = ApiConstants.PAGE_SIZE,
            ),
            initialKey = 1,
        ) {
            moviesPagingSource
        }.flow
    }

    override suspend fun getMovie(movieId: Long): MovieDetail {
        try {
            return moviesApi.getMovie(movieId).toEntity()
        } catch (throwable: Throwable) {
            throw exceptionResolver.resolve(throwable)
        }
    }

}