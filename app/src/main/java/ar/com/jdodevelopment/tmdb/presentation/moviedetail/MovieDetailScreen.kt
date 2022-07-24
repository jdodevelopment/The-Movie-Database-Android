package ar.com.jdodevelopment.tmdb.presentation.moviedetail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController


@Composable
fun MovieDetailScreen(
    navController: NavHostController,
    movieId: Long,
    viewModel: MovieDetailViewModel = hiltViewModel(),
) {
    Text(text = "MovieDetail $movieId")
}