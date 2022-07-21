package ar.com.jdodevelopment.tmdb.domain.error

import ar.com.jdodevelopment.tmdb.domain.entity.Error

interface ErrorResolver {

    fun resolve(throwable: Throwable): Error

}