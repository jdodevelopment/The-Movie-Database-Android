package ar.com.jdodevelopment.tmdb.domain.usecase

import ar.com.jdodevelopment.tmdb.domain.entity.MovieDetail
import ar.com.jdodevelopment.tmdb.domain.error.ResourceException
import ar.com.jdodevelopment.tmdb.domain.repository.MoviesRepository
import ar.com.jdodevelopment.tmdb.domain.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: MoviesRepository,
) {

    operator fun invoke(movieId: Long): Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading(true))
            val movie = repository.getMovie(movieId)
            emit(Resource.Success(movie))
        } catch (exception: ResourceException) {
            emit(Resource.Error(exception))
        } catch (throwable: Throwable) {
            emit(Resource.Error(ResourceException.Unknown(null)))
        }
        emit(Resource.Loading(false))
    }

}