package ar.com.jdodevelopment.tmdb.di

import ar.com.jdodevelopment.tmdb.data.repository.DefaultMoviesRepository
import ar.com.jdodevelopment.tmdb.domain.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideMoviesRepository(repository: DefaultMoviesRepository): MoviesRepository

}