package ar.com.jdodevelopment.tmdb.data.dto

import ar.com.jdodevelopment.tmdb.domain.entity.Genre


data class GenreDTO(
    val id: Int,
    val name: String,
)

fun GenreDTO.toEntity(): Genre {
    return Genre(
        id = id,
        name = name,
    )
}