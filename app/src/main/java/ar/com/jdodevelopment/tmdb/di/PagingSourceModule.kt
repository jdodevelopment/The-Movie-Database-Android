package ar.com.jdodevelopment.tmdb.di

import androidx.paging.PagingSource
import ar.com.jdodevelopment.tmdb.data.paging.MoviesPagingSource
import ar.com.jdodevelopment.tmdb.domain.entity.Movie
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PagingSourceModule {

    @Binds
    abstract fun bindMoviesPagingSource(moviesPagingSource: MoviesPagingSource): PagingSource<Int, Movie>

}