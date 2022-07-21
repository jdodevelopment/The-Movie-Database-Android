package ar.com.jdodevelopment.tmdb.di

import ar.com.jdodevelopment.tmdb.data.api.MoviesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provide(retrofit: Retrofit): MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }

}