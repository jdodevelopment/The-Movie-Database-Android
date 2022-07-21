package ar.com.jdodevelopment.tmdb.di

import ar.com.jdodevelopment.tmdb.data.error.DefaultErrorResolver
import ar.com.jdodevelopment.tmdb.domain.error.ErrorResolver
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ErrorResolverModule {

    @Binds
    @Singleton
    abstract fun bindErrorResolver(errorResolver: DefaultErrorResolver): ErrorResolver

}