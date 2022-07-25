package ar.com.jdodevelopment.tmdb.di

import ar.com.jdodevelopment.tmdb.data.error.DefaultExceptionResolver
import ar.com.jdodevelopment.tmdb.domain.error.ExceptionResolver
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ExceptionResolverModule {

    @Binds
    @Singleton
    abstract fun bindExceptionResolver(exceptionResolver: DefaultExceptionResolver): ExceptionResolver

}