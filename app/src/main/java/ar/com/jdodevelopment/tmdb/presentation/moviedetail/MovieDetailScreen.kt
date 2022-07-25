package ar.com.jdodevelopment.tmdb.presentation.moviedetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.com.jdodevelopment.tmdb.BuildConfig
import ar.com.jdodevelopment.tmdb.R
import ar.com.jdodevelopment.tmdb.presentation.components.VoteAverageIndicator
import coil.compose.rememberImagePainter
import coil.size.Scale


@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    if (state.movieDetail != null) {
        MovieDetail(state.movieDetail)
    }
}

@Composable
private fun MovieDetail(movieDetail: MovieDetailState) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column {
            MovieDetailBanner(movieDetail)
            MovieDetailHeader(movieDetail)
            MovieDetailBody(movieDetail)
        }
    }
}

@Composable
private fun MovieDetailBanner(movieDetail: MovieDetailState) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(256.dp)

    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = rememberImagePainter(
                data = BuildConfig.POSTER_URL + movieDetail.backdropPath,
                builder = {
                    placeholder(R.drawable.image_placerholder)
                    crossfade(true)
                    scale(Scale.FILL)
                },
            ),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Row {
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp)
                    .aspectRatio(0.6f)
                    .clip(RoundedCornerShape(8.dp)),
                painter = rememberImagePainter(
                    data = BuildConfig.POSTER_URL + movieDetail.posterPath,
                    builder = {
                        placeholder(R.drawable.image_placerholder)
                        crossfade(true)
                        scale(Scale.FILL)
                    },
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
private fun MovieDetailHeader(movieDetail: MovieDetailState) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row() {
            Text(
                text = movieDetail.title,
                style = MaterialTheme.typography.h5,
            )
            Text(
                text = movieDetail.releaseYear,
                style = MaterialTheme.typography.h5,
                color = Color.DarkGray
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            VoteAverageIndicator(voteAverage = movieDetail.voteAverage)
            Text(text = stringResource(id = R.string.vote_average))
        }
        Text(text = movieDetail.genres)
    }
}

@Composable
fun MovieDetailBody(movieDetail: MovieDetailState) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.overview),
            style = MaterialTheme.typography.h6,
        )
        Text(text = movieDetail.overview)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.status),
            style = MaterialTheme.typography.h6,
        )
        Text(text = movieDetail.status)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.original_language),
            style = MaterialTheme.typography.h6,
        )
        Text(text = movieDetail.originalLanguage)
    }
}

@Preview
@Composable
fun Preview() {
    val movieDetail = MovieDetailState(
        backdropPath = "/qTkJ6kbTeSjqfHCFCmWnfWZJOtm.jpg",
        genres = "Suspenso, Drama",
        id = 1,
        originalLanguage = "EN",
        overview = "When a mysterious animal attack leaves a mutilated body in the forest, a conservative small town detective must enlist the help of an eager wildlife specialist to uncover the dark and disturbing truth that threatens the town.",
        posterPath = "/wKiOkZTN9lUUUNZLmtnwubZYONg.jpg",
        status = "Released",
        title = "Jurassic World",
        voteAverage = 7.4,
        releaseYear = "2020"
    )
    MovieDetail(movieDetail)
}