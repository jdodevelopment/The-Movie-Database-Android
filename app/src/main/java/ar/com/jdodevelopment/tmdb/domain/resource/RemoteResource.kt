package ar.com.jdodevelopment.tmdb.domain.resource

import ar.com.jdodevelopment.tmdb.domain.entity.Error


sealed class RemoteResource<T> {

    data class Success<T>(val data: T) : RemoteResource<T>()
    data class Failure<T>(val error: Error) : RemoteResource<T>()

}