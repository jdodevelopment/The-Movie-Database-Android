package ar.com.jdodevelopment.tmdb.presentation.moviedetail

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.com.jdodevelopment.tmdb.domain.resource.Resource
import ar.com.jdodevelopment.tmdb.domain.usecase.GetMovieDetailUseCase
import ar.com.jdodevelopment.tmdb.presentation.navigation.MOVIE_ID_PARAM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieDetailUseCase: GetMovieDetailUseCase
) : ViewModel() {

    private val movieId = savedStateHandle.get<Long>(MOVIE_ID_PARAM)!!

    private val _state = mutableStateOf(MovieDetailScreenState())
    val state: State<MovieDetailScreenState> = _state

    init {
        getMovieDetails()
    }

    fun getMovieDetails() {
        getMovieDetailUseCase(movieId).onEach {
            _state.value = when (it) {
                is Resource.Loading -> {
                    _state.value.copy(loading = it.isLoading)
                }
                is Resource.Success -> {
                    _state.value.copy(movieDetail = it.data.toMovieDetailState())
                }
                is Resource.Error -> {
                    _state.value.copy(error = it.error.toMovieDetailErrorState())
                }
            }
        }.launchIn(viewModelScope)
    }

}