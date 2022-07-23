package ar.com.jdodevelopment.tmdb.domain.usecase

import androidx.paging.PagingData
import ar.com.jdodevelopment.tmdb.domain.entity.Movie
import ar.com.jdodevelopment.tmdb.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MoviesRepository,
) {

    operator fun invoke(page: Long): Flow<PagingData<Movie>> {
        return repository.getPopularMovies(page)
    }

}