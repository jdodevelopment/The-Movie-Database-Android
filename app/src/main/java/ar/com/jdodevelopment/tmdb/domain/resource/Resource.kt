package ar.com.jdodevelopment.tmdb.domain.resource

import ar.com.jdodevelopment.tmdb.domain.error.ResourceException


sealed class Resource<T> {

    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val error: ResourceException) : Resource<T>()
    class Loading<T>(val isLoading: Boolean) : Resource<T>()

}