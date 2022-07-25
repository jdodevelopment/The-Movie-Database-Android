package ar.com.jdodevelopment.tmdb.data.dto

import ar.com.jdodevelopment.tmdb.domain.entity.Movie
import com.google.gson.annotations.SerializedName

data class MovieDTO(
    val adult: Boolean?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("genre_ids")
    val genreIds: List<Int>?,
    val id: Int?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("original_title")
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?,
)

fun MovieDTO.toEntity(): Movie {
    return Movie(
        adult = adult ?: false,
        backdropPath = backdropPath ?: "",
        genreIds = genreIds ?: listOf(),
        id = id ?: -1,
        originalLanguage = originalLanguage ?: "",
        originalTitle = originalTitle ?: "",
        overview = overview ?: "",
        popularity = popularity ?: 0.0,
        posterPath = posterPath ?: "",
        releaseDate = releaseDate ?: "-",
        title = title ?: "",
        video = video ?: false,
        voteAverage = voteAverage,
        voteCount = voteCount ?: 0,
    )
}