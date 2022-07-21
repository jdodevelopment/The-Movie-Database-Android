package ar.com.jdodevelopment.tmdb.data.dto

import com.google.gson.annotations.SerializedName

data class HttpErrorResponseDTO(
    @SerializedName("status_message")
    val statusMessage: String,
    @SerializedName("status_code")
    val statusCode: Int,
)
