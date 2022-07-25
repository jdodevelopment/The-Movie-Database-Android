package ar.com.jdodevelopment.tmdb.data.dto

import ar.com.jdodevelopment.tmdb.domain.entity.MovieDetail
import com.google.gson.annotations.SerializedName

data class MovieDetailDTO(
    val adult: Boolean?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    val budget: Int?,
    val genres: List<GenreDTO>?,
    val homepage: String?,
    val id: Int?,
    @SerializedName("imdb_id")
    val imdbId: String?,
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
    val revenue: Int?,
    val runtime: Int?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?
)

fun MovieDetailDTO.toEntity(): MovieDetail {
    return MovieDetail(
        adult = adult ?: false,
        backdropPath = backdropPath ?: "",
        budget = budget ?: 0,
        genres = genres?.map { it.toEntity() } ?: listOf(),
        homepage = homepage ?: "",
        id = id ?: -1,
        imdbId = imdbId ?: "",
        originalLanguage = originalLanguage ?: "",
        originalTitle = originalTitle ?: "",
        overview = overview ?: "",
        popularity = popularity ?: 0.0,
        posterPath = posterPath ?: "",
        releaseDate = releaseDate ?: "-",
        revenue = revenue ?: 0,
        runtime = runtime ?: 0,
        status = status ?: "Unknow",
        tagline = tagline ?: "",
        title = title ?: "",
        video = video ?: false,
        voteAverage = voteAverage,
        voteCount = voteCount ?: 0,
    )
}