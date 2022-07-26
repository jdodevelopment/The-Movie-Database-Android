package ar.com.jdodevelopment.tmdb.presentation.popularmovies

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ar.com.jdodevelopment.tmdb.domain.error.ExceptionResolver
import ar.com.jdodevelopment.tmdb.domain.error.ResourceException
import ar.com.jdodevelopment.tmdb.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val exceptionResolver: ExceptionResolver,
) : ViewModel() {

    val movies = getPopularMoviesUseCase()

    private val _state = mutableStateOf(PopularMoviesScreenState())
    val state: State<PopularMoviesScreenState> = _state

    fun onError(throwable: Throwable) {
        if (throwable is ResourceException) {
            _state.value = _state.value.copy(error = throwable.toPopularMoviesErrorState())
        } else {
            val resourceException = exceptionResolver.resolve(throwable)
            _state.value = _state.value.copy(error = resourceException.toPopularMoviesErrorState())
        }
    }

    fun onRetry() {
        _state.value = _state.value.copy(error = null)
    }
}