package ar.com.jdodevelopment.tmdb.domain.usecase

import ar.com.jdodevelopment.tmdb.domain.entity.MoviesPage
import ar.com.jdodevelopment.tmdb.domain.repository.MoviesRepository
import ar.com.jdodevelopment.tmdb.domain.resource.RemoteResource
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MoviesRepository,
) {

    suspend operator fun invoke(page: Long): RemoteResource<MoviesPage> {
        return repository.getPopularMovies(page)
    }

}