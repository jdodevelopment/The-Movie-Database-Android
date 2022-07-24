package ar.com.jdodevelopment.tmdb.presentation.popularmovies

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import ar.com.jdodevelopment.tmdb.BuildConfig
import ar.com.jdodevelopment.tmdb.R
import ar.com.jdodevelopment.tmdb.domain.entity.Movie
import ar.com.jdodevelopment.tmdb.presentation.components.VoteAverageIndicator
import ar.com.jdodevelopment.tmdb.presentation.navigation.Routes
import coil.compose.rememberImagePainter
import coil.size.Scale


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PopularMoviesScreen(
    navController: NavHostController,
    viewModel: PopularMoviesViewModel = hiltViewModel()
) {
    val lazyPagingItems = viewModel.movies.collectAsLazyPagingItems()
    LazyVerticalGrid(
        cells = GridCells.Adaptive(192.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(lazyPagingItems.itemCount) { index ->
            val movie = lazyPagingItems[index]
            if (movie != null) {
                MovieItem(movie) {
                    val route = Routes.MovieDetail.withParams(movie.id)
                    navController.navigate(route)
                }
            }
        }
    }
}

@Composable
private fun MovieItem(
    movie: Movie,
    onMovieClick: (movie: Movie) -> Unit
) {
    Card(
        modifier = Modifier
            .clickable { onMovieClick(movie) }
            .padding(8.dp),
    ) {
        Column {
            Box {
                Image(
                    modifier = Modifier
                        .aspectRatio(0.66f)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp)),
                    painter = rememberImagePainter(
                        data = BuildConfig.POSTER_URL + movie.posterPath,
                        builder = {
                            placeholder(R.drawable.image_placerholder)
                            crossfade(true)
                            scale(Scale.FILL)
                        },
                    ),

                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                VoteAverageIndicator(
                    voteAverage = movie.voteAverage,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                )
            }
            Column(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.body1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = movie.releaseDate,
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
    }
}

@Preview()
@Composable
fun Preview() {
    val movie = Movie(
        adult = true,
        backdropPath = "kAVRgw7GgK1CfYEJq8ME6EvRIgU.jpg",
        genreIds = listOf(),
        id = 1,
        originalLanguage = "String",
        originalTitle = "String",
        overview = "String",
        popularity = 0.1,
        posterPath = "kAVRgw7GgK1CfYEJq8ME6EvRIgU.jpg",
        releaseDate = "10/10/2022",
        title = "Jurassic World",
        video = false,
        voteAverage = 7.4,
        voteCount = 100,
    )
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box() {
            MovieItem(movie) {
                println(movie.title)
            }
        }
    }

}