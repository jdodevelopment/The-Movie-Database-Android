package ar.com.jdodevelopment.tmdb.presentation.popularmovies

import androidx.lifecycle.ViewModel
import ar.com.jdodevelopment.tmdb.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
) : ViewModel() {

    val movies = getPopularMoviesUseCase()

}