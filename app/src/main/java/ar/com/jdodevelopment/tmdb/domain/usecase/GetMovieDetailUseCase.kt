package ar.com.jdodevelopment.tmdb.domain.usecase

import ar.com.jdodevelopment.tmdb.domain.entity.MovieDetail
import ar.com.jdodevelopment.tmdb.domain.repository.MoviesRepository
import ar.com.jdodevelopment.tmdb.domain.resource.RemoteResource
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: MoviesRepository,
) {

    suspend operator fun invoke(movieId: Long): RemoteResource<MovieDetail> {
        return repository.getMovie(movieId)
    }

}