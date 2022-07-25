package ar.com.jdodevelopment.tmdb.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.com.jdodevelopment.tmdb.ui.theme.Dark


@Composable
fun VoteAverageIndicator(
    voteAverage: Double?,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(Dark, shape = CircleShape)
            .size(40.dp)
    ) {
        CircularProgressIndicator(
            progress = (voteAverage ?: 0).toFloat() / 10,
            color = resolveColor(voteAverage ?: 0.0),
            strokeWidth = 2.dp,
            modifier = Modifier
                .size(35.dp)
                .align(Alignment.Center)
        )
        Text(
            text = if(voteAverage != null) formatPercent(voteAverage) else "-",
            color = Color.White,
            modifier = Modifier.align(Alignment.Center),
            fontSize = 11.sp
        )
    }
}

private fun resolveColor(voteAverage: Double): Color {
    return when {
        voteAverage < 4f -> Color.Red
        voteAverage < 7f -> Color.Yellow
        else -> Color.Green
    }
}

private fun formatPercent(voteAverage: Double): String {
    return (voteAverage * 10).toInt().toString() + "%"
}

@Preview
@Composable
private fun Preview() {
    VoteAverageIndicator(0.9)
}