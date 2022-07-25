package ar.com.jdodevelopment.tmdb.domain.error

interface ExceptionResolver {

    fun resolve(throwable: Throwable): ResourceException

}