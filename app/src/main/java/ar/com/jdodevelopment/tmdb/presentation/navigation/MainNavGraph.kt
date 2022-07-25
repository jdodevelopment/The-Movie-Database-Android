package ar.com.jdodevelopment.tmdb.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ar.com.jdodevelopment.tmdb.presentation.moviedetail.MovieDetailScreen
import ar.com.jdodevelopment.tmdb.presentation.popularmovies.PopularMoviesScreen

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.PopularMovies.value,
    ) {
        composable(Routes.PopularMovies.value) {
            PopularMoviesScreen(navController)
        }
        composable(
            route = Routes.MovieDetail.value,
            arguments = listOf(
                navArgument(MOVIE_ID_PARAM) {
                    type = NavType.LongType
                    nullable = false
                }
            )
        ) {
            MovieDetailScreen()
        }
    }
}